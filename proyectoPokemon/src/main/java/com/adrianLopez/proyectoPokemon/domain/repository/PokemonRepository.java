package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;

@Repository
public interface PokemonRepository {

    public List<PokemonDTO> getAll(Integer page, Integer pageSize);

    public Optional<PokemonDTO> find(int id);

    public int getTotalNumberOfRecords();

}
