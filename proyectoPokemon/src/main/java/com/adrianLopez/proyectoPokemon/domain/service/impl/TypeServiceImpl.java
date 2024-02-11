package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.common.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.domain.mapper.TypeDomainMapper;
import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.domain.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Override
    public TypeDTO find(int id) {
        return TypeDomainMapper.mapper
                .toTypeDTO(this.findType(id));
    }

    @Override
    public Stream<TypeDTO> findAll() {
        return typeRepository.findAll()
                .map(TypeDomainMapper.mapper::toTypeDTO);
    }

    private Type findType(int id) {
        return typeRepository
                .find(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Type not found with id: " + id));

    }

    @Override
    public TypeDTO create(TypeDTO typeDTO) {
        return TypeDomainMapper.mapper
                .toTypeDTO(
                        typeRepository
                                .save(
                                        TypeDomainMapper.mapper
                                                .toType(typeDTO)));
    }

    @Override
    public TypeDTO update(TypeDTO typeDTO) {
        Type type = this.findType(typeDTO.getId());
        TypeDomainMapper.mapper.updateTypeFromTypeDTO(typeDTO, type);
        return TypeDomainMapper.mapper
                .toTypeDTO(
                        typeRepository
                                .save(type));
    }

    @Override
    public void delete(int id) {
        this.findType(id);
        typeRepository.delete(id);
    }

}
