package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.exception.NotValidCombinationException;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        List<Pokemon> pokemons = pokemonRepository.getAll(page, pageSize).stream()
                .map(PokemonMapper.mapper::toPokemon).toList();
        return pokemons.stream()
                .map(PokemonMapper.mapper::toPokemonDTO).toList();
    }

    @Override
    public List<PokemonDTO> getAll() {
        List<Pokemon> pokemons = pokemonRepository.getAll(null, null).stream()
                .map(PokemonMapper.mapper::toPokemon).toList();
        return pokemons.stream()
                .map(PokemonMapper.mapper::toPokemonDTO).toList();
    }

    @Override
    public PokemonDTO find(int id) {
        Pokemon pokemon = PokemonMapper.mapper.toPokemon(pokemonRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon no encontrado con id: " + id)));
        return PokemonMapper.mapper.toPokemonDTO(pokemon);
    }

    @Override
    public int getTotalNumberOfRecords() {
        return pokemonRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(PokemonDTO pokemonDTO) {
        Pokemon pokemon = PokemonMapper.mapper.toPokemon(pokemonDTO);
        if (pokemonRepository.exists(pokemon.getId())) {
            throw new ResourceNotFoundException("El pokemon con id " + pokemonDTO.getId() + " ya existe");
        }
        return pokemonRepository.insert(PokemonMapper.mapper.toPokemonDTO(pokemon));
    }

    @Override
    public void delete(int id) {
        if (!pokemonRepository.exists(id)) {
            throw new ResourceNotFoundException("Pokemon no encontrado con id: " + id);
        }
        pokemonRepository.delete(id);
    }

    @Override
    public void update(PokemonDTO pokemonDTO) {
        Pokemon pokemon = PokemonMapper.mapper.toPokemon(pokemonDTO);
        if (!pokemonRepository.exists(pokemon.getId())) {
            throw new ResourceNotFoundException("Pokemon no encontrado con id: " + pokemonDTO.getId());
        }
        pokemonRepository.update(PokemonMapper.mapper.toPokemonDTO(pokemon));
    }

    @Override
    public int createPokemonType(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemon slotPokemon = SlotPokemonMapper.mapper.toSlotPokemon(slotPokemonDTO);
        Pokemon pokemon = PokemonMapper.mapper.toPokemon(pokemonRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon no encontrado con id: " + id)));
        if (pokemon.getSlotPokemons() != null) {
            for (SlotPokemon slotPokemon2 : pokemon.getSlotPokemons()) {
                if (slotPokemon2.getType().getId() == slotPokemon.getType().getId())
                    throw new NotValidCombinationException("El pokemon no puede tener 2 tipos iguales");
            }
        }
        if (!pokemon.addSlotPokemon(slotPokemon)) {
            throw new ResourceNotFoundException("El pokemon ya posee un tipo en el slot: " + slotPokemon.getSlot());
        }
        return pokemonRepository.insertPokemonType(SlotPokemonMapper.mapper.toSlotPokemonDTO(slotPokemon), id);
    }

    @Override
    public void updatePokemonType(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemon slotPokemon = SlotPokemonMapper.mapper.toSlotPokemon(slotPokemonDTO);
        Pokemon pokemon = PokemonMapper.mapper.toPokemon(pokemonRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon no encontrado con id: " + id)));
        if (pokemon.getSlotPokemons() != null) {
            for (SlotPokemon slotPokemon2 : pokemon.getSlotPokemons()) {
                if (slotPokemon2.getType().equals(slotPokemon.getType()))
                    throw new NotValidCombinationException("El pokemon no puede tener 2 tipos iguales");
            }
        }
        if (!pokemon.updateSlotPokemon(slotPokemon)) {
            throw new ResourceNotFoundException("El pokemon no posee un tipo en el slot: " + slotPokemon.getSlot());
        }
        pokemonRepository.updatePokemonType(SlotPokemonMapper.mapper.toSlotPokemonDTO(slotPokemon), id);
    }

    @Override
    public void deletePokemonTypes(int id) {
        Pokemon pokemon = PokemonMapper.mapper.toPokemon(pokemonRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon no encontrado con id: " + id)));
        pokemon.setSlotPokemons(null);
        pokemonRepository.deletePokemonType(id);
    }

}