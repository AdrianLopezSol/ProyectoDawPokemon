package com.adrianLopez.proyectoPokemon.persistance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.Type;

@Mapper(componentModel = "spring")
public interface TypePersistanceMapper {

    TypePersistanceMapper mapper = Mappers.getMapper(TypePersistanceMapper.class);

    Type toType(TypeDTO typeDTO);

    TypeDTO toTypeDTO(Type type);
    
}
