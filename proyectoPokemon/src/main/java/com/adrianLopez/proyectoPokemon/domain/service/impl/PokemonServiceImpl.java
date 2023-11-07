package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        return pokemonRepository.getAll(page, pageSize);
    }

    @Override
    public List<PokemonDTO> getAll() {
        return pokemonRepository.getAll(null, null);
    }

    @Override
    public PokemonDTO find(int id) {
        PokemonDTO pokemonDTO = pokemonRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found with id: " + id));
        return pokemonDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return pokemonRepository.getTotalNumberOfRecords();
    }
}