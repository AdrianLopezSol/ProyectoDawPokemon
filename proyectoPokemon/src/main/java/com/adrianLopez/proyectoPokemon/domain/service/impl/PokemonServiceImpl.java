package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        List<Pokemon> pokemons = pokemonRepository.getAll(page, pageSize).stream()
        .map(PokemonMapper.mapper::toPokemon).
        toList();
        return pokemons.stream()
        .map(PokemonMapper.mapper::toPokemonDTO).
        toList();
    }

    @Override
    public List<PokemonDTO> getAll() {
        List<Pokemon> pokemons = pokemonRepository.getAll(null, null).stream()
        .map(PokemonMapper.mapper::toPokemon).
        toList();
        return pokemons.stream()
        .map(PokemonMapper.mapper::toPokemonDTO).
        toList();
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
        if (pokemonRepository.exists(pokemon.getId())){
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

}