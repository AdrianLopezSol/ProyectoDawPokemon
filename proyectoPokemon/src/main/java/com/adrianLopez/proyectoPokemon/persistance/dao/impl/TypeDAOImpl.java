package com.adrianLopez.proyectoPokemon.persistance.dao.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.persistance.dao.TypeDAO;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.jpaRepository.TypeJpaRepository;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.mapper.TypeEntityMapper;

@Component
public class TypeDAOImpl implements TypeDAO {

    @Autowired
    TypeJpaRepository typeJpaRepository;

    @Override
    public Stream<TypeDTO> findAll() {
        return typeJpaRepository
                .findAll()
                .stream()
                .map(TypeEntityMapper.mapper::toTypeDTO);
    }

    @Override
    public Optional<TypeDTO> find(int id) {
        return Optional.ofNullable(
            TypeEntityMapper
                        .mapper
                        .toTypeDTO(
                            typeJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Optional<TypeDTO> findByPokemonTypeId(int id) {
        return Optional.ofNullable(
            TypeEntityMapper
                        .mapper
                        .toTypeDTO(
                            typeJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public TypeDTO save(TypeDTO typeDTO) {
        return TypeEntityMapper
                .mapper
                .toTypeDTO(
                    typeJpaRepository
                                .save(
                                    TypeEntityMapper
                                                .mapper
                                                .toTypeEntity(typeDTO)
                                )
                );
    }

    @Override
    public void delete(int id) {
        typeJpaRepository.deleteById(id);
    }
    
}
