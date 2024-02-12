package com.adrianLopez.proyectoPokemon.persistance.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;

@Component
public interface TypeDAO {

    public Stream<TypeDTO> findAll();

    public Optional<TypeDTO> findByPokemonTypeId(int id);

    public void delete(int id);

    public TypeDTO save(TypeDTO typeDTO);

    public Optional<TypeDTO> find(int id);

}
