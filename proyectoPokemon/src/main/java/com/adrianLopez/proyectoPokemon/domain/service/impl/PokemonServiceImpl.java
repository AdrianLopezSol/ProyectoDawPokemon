package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.common.exception.BadRequestException;
import com.adrianLopez.proyectoPokemon.common.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.domain.entity.Stats;
import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.domain.mapper.PokemonDomainMapper;
import com.adrianLopez.proyectoPokemon.domain.mapper.SlotPokemonDomainMapper;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    TypeRepository typeRepository;

    @Override
    public Stream<PokemonDTO> getAll(Integer page, Integer pageSize) {
        return pokemonRepository
                .getAll(page, pageSize)
                .map(PokemonDomainMapper.mapper::toPokemonDTO);
    }

    @Override
    public Stream<PokemonDTO> getAll() {
        return  pokemonRepository.getAll(null, null)
                .map(PokemonDomainMapper.mapper::toPokemonDTO);
    }


    @Override
    public PokemonDTO find(int id) {
        return PokemonDomainMapper
                .mapper
                .toPokemonDTO(
                        this.findPokemon(id)
                );
    }

    private Pokemon findPokemon(int id) {
        return pokemonRepository
                .find(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Pokemon not found with id: " + id)
                );
    }

    @Override
    public long getTotalNumberOfRecords() {
        return pokemonRepository.getTotalNumberOfRecords();
    }

    @Override
    public PokemonDTO create(PokemonDTO pokemonDTO) {
        Pokemon pokemon= PokemonDomainMapper.mapper.toPokemon(pokemonDTO);
        this.addStatsDTO(pokemon, pokemonDTO.getStatsDTO());

        List<SlotPokemonDTO> slotPokemonDTOs = pokemonDTO.getSlotPokemonDTOs();
        List<SlotPokemon> slotPokemons = SlotPokemonDomainMapper.mapper.toSlotPokemons(slotPokemonDTOs);
        slotPokemons.forEach(
                slotPokemon -> {
                    slotPokemon
                            .setType(
                                    typeRepository
                                            .find(
                                                    slotPokemon.getType().getId())
                                            .orElseThrow(
                                                    () -> new ResourceNotFoundException("Type not found with id: " + slotPokemon.getType().getId())
                                            )
                    );
                }
        );
        pokemon.setSlotPokemons(slotPokemons);


        return PokemonDomainMapper
                .mapper
                .toPokemonDTO(
                        pokemonRepository
                                .save(pokemon)
                );
    }

    private void addStatsDTO(Pokemon pokemon, StatsDTO statsDTO) {
        if(statsDTO == null || statsDTO.getPok_id() == null) {
            throw new BadRequestException("Stats cannot be null");
        }
        Stats stats = statsRepository
                .find(statsDTO.getPok_id())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Stats not found with id: " + statsDTO.getPok_id())
                );
            pokemon.setStats(stats);
    }

    @Override
    public PokemonDTO update(PokemonDTO pokemonDTO) {
        if(pokemonDTO.getId() == null) {
            throw new BadRequestException("Pokemon cannot be null");
        }
        Pokemon pokemon = this.findPokemon(pokemonDTO.getId());
        this.addStatsDTO(pokemon, pokemonDTO.getStatsDTO());

        PokemonDomainMapper.mapper.updatePokemonFromPokemonDTO(pokemonDTO, pokemon);
        return PokemonDomainMapper
                .mapper
                .toPokemonDTO(
                        pokemonRepository.save(pokemon)
                );
    }

    @Override
    public void delete(int id) {
        this.findPokemon(id);
        pokemonRepository.delete(id);
    }

    @Override
    public PokemonDTO addPokemonType(SlotPokemonDTO slotPokemonDTO, int id) {
        Pokemon pokemon = this.findPokemon(id);
        SlotPokemon slotPokemon = this.buildPokemonType(slotPokemonDTO);
        pokemon.addPokemonType(slotPokemon);
        return PokemonDomainMapper
                .mapper
                .toPokemonDTO(
                        pokemonRepository.save(pokemon)
                );
    }

    @Override
    public PokemonDTO updatePokemonType(SlotPokemonDTO slotPokemonDTO, int id) {
        Pokemon pokemon = this.findPokemon(id);
        SlotPokemon slotPokemon = this.buildPokemonType(slotPokemonDTO);
        pokemon.updatePokemonType(slotPokemon);
        return PokemonDomainMapper
                .mapper
                .toPokemonDTO(
                    pokemonRepository.save(pokemon)
                );
    }

    private SlotPokemon buildPokemonType(SlotPokemonDTO slotPokemonDTO) {
        SlotPokemon slotPokemon = SlotPokemonDomainMapper
                .mapper
                .toSlotPokemon(slotPokemonDTO);
        Type type = typeRepository.find(
                        slotPokemon
                                .getType()
                                .getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Type not found with id: " + slotPokemonDTO.getTypeDTO().getId())
                );
                slotPokemon.setType(type);
        return slotPokemon;
    }

    @Override
    public void deletePokemonTypes(int id) {
        @SuppressWarnings("unused")
        Pokemon pokemon = this.findPokemon(id);
        pokemonRepository.deletePokemonTypes(id);
    }

}