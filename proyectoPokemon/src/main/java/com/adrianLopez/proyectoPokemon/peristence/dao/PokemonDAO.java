package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;

@Component
public interface PokemonDAO {

    public Stream<PokemonDTO> findAll(Integer page, Integer pageSize);

    public Optional<PokemonDTO> find(int id);

    public boolean existsById(int id);

    long count();

    public PokemonDTO save(PokemonDTO pokemonDTO);

    public void delete(int id);

}