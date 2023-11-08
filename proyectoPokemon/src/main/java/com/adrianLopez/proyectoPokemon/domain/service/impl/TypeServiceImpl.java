package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.domain.service.TypeService;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.mapper.TypeMapper;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<TypeDTO> findAll() {
        List<Type> types = typeRepository.findAll().stream()
        .map(TypeMapper.mapper::toType).
        toList();
        return types.stream()
        .map(TypeMapper.mapper::toTypeDTO).
        toList();
    }

    @Override
    public int create(TypeDTO typeDTO) {
        Type type = TypeMapper.mapper.toType(typeDTO);
        if (typeRepository.find(type.getId()).isPresent()){
            throw new ResourceNotFoundException("Tipo ya existente con id: " + type.getId());
        }
        return typeRepository.insert(TypeMapper.mapper.toTypeDTO(type));
    }

    @Override
    public void update(TypeDTO typeDTO) {
        Type type = TypeMapper.mapper.toType(typeDTO);
        typeRepository.find(type.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Tipo no encontrado con id: " + type.getId()));
        typeRepository.update(TypeMapper.mapper.toTypeDTO(type));
    }

    @Override
    public void delete(int id) {
        typeRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Tipo no encontrado con el id: " + id));
        typeRepository.delete(id);
    }
    
    @Override
    public TypeDTO find(int id) {
        Type type = TypeMapper.mapper.toType(typeRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo no encontrado con id: " + id)));
        return TypeMapper.mapper.toTypeDTO(type);
    }

}
