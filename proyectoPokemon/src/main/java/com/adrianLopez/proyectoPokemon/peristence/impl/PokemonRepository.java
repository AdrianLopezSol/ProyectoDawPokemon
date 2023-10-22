package com.adrianLopez.proyectoPokemon.peristence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;

@Repository
public interface PokemonRepository {

    public List<Pokemon> getAll();

    public Optional<Pokemon> find(int id);


    
}
