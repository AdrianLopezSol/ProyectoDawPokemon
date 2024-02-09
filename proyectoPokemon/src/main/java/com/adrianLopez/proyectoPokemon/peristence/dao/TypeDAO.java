package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Component
public interface TypeDAO {

    public Stream<TypeDTO> findAll();

    public Optional<TypeDTO> findByPokemonIdAndSlot(int id, int slot);

    public void delete(int id);

    public TypeDTO save(TypeDTO typeDTO);

    public Optional<TypeDTO> find(int id);

}
