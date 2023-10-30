package com.adrianLopez.proyectoPokemon.domain.repository;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.StatsDTO;

@Repository
public interface StatsRepository {

    public StatsDTO findByPokemonId(int id);
    
}