package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;

@Service
public interface PokemonService {

     public List<PokemonDTO> getAll(Integer page, Integer pageSize);

     public List<PokemonDTO> getAll();

     public PokemonDTO find(int id);

     int getTotalNumberOfRecords();
    
}
