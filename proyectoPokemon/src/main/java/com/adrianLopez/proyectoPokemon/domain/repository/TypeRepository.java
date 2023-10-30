package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Repository
public interface TypeRepository {

    public List<TypeDTO> findByPokemonId(int id);

    public List<TypeDTO> findAll();

}