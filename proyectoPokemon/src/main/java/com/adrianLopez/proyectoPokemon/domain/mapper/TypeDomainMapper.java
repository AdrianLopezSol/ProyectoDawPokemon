package com.adrianLopez.proyectoPokemon.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.Type;

@Mapper(componentModel = "spring")
public interface TypeDomainMapper {
    TypeDomainMapper mapper = Mappers.getMapper(TypeDomainMapper.class);

    Type toType(TypeDTO typeDTO);

    TypeDTO toTypeDTO(Type type);

    void updateTypeFromTypeDTO(TypeDTO typeDTO, @MappingTarget Type type);

}
