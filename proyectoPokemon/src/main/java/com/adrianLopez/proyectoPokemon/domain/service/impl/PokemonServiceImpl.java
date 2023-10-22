package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.peristence.impl.PokemonRepository;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;
 
    @Override
    public List<Pokemon> getAll() {
        return pokemonRepository.getAll();
    }
 
    @Override
    public Optional<Pokemon> find(int id) {
        Optional<Pokemon> pokemon = pokemonRepository.find(id);
 
        return pokemon;
    }
}