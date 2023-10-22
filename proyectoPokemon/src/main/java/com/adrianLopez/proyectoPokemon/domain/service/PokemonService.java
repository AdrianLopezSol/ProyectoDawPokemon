package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;

@Service
public interface PokemonService {

     public List<Pokemon> getAll();

     public Optional<Pokemon> find(int id);
    
}
