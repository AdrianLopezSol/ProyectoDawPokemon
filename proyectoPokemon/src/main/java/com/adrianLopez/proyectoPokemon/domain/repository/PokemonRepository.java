package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;

@Repository
public interface PokemonRepository {

    public List<PokemonDTO> getAll(Integer page, Integer pageSize);

    public PokemonDTO find(int id);


    
}
