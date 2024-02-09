package com.adrianLopez.proyectoPokemon.peristence.dao.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.peristence.dao.PokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.impl.jpaRepository.PokemonJpaRepository;
import com.adrianLopez.proyectoPokemon.peristence.dao.impl.mapper.PokemonEntityMapper;

@Component
public class PokemonDAOImpl implements PokemonDAO {

    @Autowired
    PokemonJpaRepository pokemonJpaRepository;

    @Override
    public Stream<PokemonDTO> findAll(Integer page, Integer pageSize) {
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            return pokemonJpaRepository
                    .findAll(pageable)
                    .stream()
                    .map(PokemonEntityMapper.mapper::toPokemonDTO);
        }
        return pokemonJpaRepository
                .findAll()
                .stream()
                .map(PokemonEntityMapper.mapper::toPokemonDTO);
    }

    @Override
    public Optional<PokemonDTO> find(int id) {
        return Optional.ofNullable(
            PokemonEntityMapper
                        .mapper
                        .toPokemonDtoWithStatsAndPokemonTypes(
                                pokemonJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public long count() {
        return pokemonJpaRepository.count();
    }

    @Override
    public PokemonDTO save(PokemonDTO pokemonDTO) {
        return PokemonEntityMapper
                .mapper
                .toPokemonDtoWithStatsAndPokemonTypes(
                        pokemonJpaRepository
                                .save(
                                    PokemonEntityMapper
                                                .mapper
                                                .toPokemonEntity(pokemonDTO)
                                )
                );
    }

    @Override
    public void delete(int id) {
        pokemonJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return pokemonJpaRepository.existsById(id);
    }
    
}
