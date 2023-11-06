package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.domain.service.TypeService;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    public List<TypeDTO> findAll(){
        return typeRepository.findAll();
        
    }
    
}
