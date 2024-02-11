package com.adrianLopez.proyectoPokemon.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.presentation.request.TypeRequest;
import com.adrianLopez.proyectoPokemon.presentation.response.TypeResponse;

@Mapper(componentModel = "spring")
public interface TypePresentationMapper {

    TypePresentationMapper mapper = Mappers.getMapper(TypePresentationMapper.class);

    @Mapping(target = "link", ignore = true )
    TypeResponse toTypeResponse(TypeDTO typeDTO);

    TypeDTO toTypeDTO(TypeRequest typeRequest);
}
