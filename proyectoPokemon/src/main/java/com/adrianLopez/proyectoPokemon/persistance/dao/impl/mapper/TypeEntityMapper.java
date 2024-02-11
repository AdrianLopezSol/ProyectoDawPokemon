package com.adrianLopez.proyectoPokemon.persistance.dao.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.model.TypeEntity;

@Mapper(componentModel = "spring")
public interface TypeEntityMapper {

    TypeEntityMapper mapper = Mappers.getMapper(TypeEntityMapper.class);

    TypeDTO toTypeDTO(TypeEntity typeEntity);

    TypeEntity toTypeEntity(TypeDTO typeDTO);
    
}
