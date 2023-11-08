package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Repository
public interface TypeRepository {

    public List<TypeDTO> findAll();

    public Optional<TypeDTO> find(int id);

    public void delete(int id);

    public void update(TypeDTO typeDTO);

    public int insert(TypeDTO typeDTO);

}