package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.domain.service.TypeService;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    public List<TypeDTO> findAll(){
        return typeRepository.findAll();
    }

    @Override
    public int create(TypeDTO typeDTO) {
        return typeRepository.insert(typeDTO);
    }

    @Override
    public void update(TypeDTO typeDTO) {
        typeRepository.find(typeDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Tipo no encontrado con el id: " + typeDTO.getId()));
        typeRepository.update(typeDTO);
    }

    @Override
    public void delete(int id) {
        typeRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Tipo no encontrado con el id: " + id));
        typeRepository.delete(id);
    }

    @Override
    public TypeDTO find(int id) {
        return typeRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
    }
    
    
}
