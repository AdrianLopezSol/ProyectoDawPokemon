package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Service
public interface TypeService {

    public List<TypeDTO> findAll();

}
