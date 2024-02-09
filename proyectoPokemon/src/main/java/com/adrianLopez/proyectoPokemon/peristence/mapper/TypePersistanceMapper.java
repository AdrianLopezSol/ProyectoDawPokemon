package com.adrianLopez.proyectoPokemon.peristence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Mapper(componentModel = "spring")
public interface TypePersistanceMapper {

    TypePersistanceMapper mapper = Mappers.getMapper(TypePersistanceMapper.class);

    Type toType(TypeDTO typeDTO);

    TypeDTO toTypeDTO(Type type);
    
}
