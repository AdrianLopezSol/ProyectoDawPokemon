package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;

@Service
public interface TypeService {

    public Stream<TypeDTO> findAll(); 

    public TypeDTO find(int id);

    public void delete(int id);

    public TypeDTO update(TypeDTO typeDTO);

    public TypeDTO create(TypeDTO typeDTO);

}
