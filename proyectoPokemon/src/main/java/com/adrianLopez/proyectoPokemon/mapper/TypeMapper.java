package com.adrianLopez.proyectoPokemon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;
import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.peristence.model.TypeEntity;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    TypeMapper mapper = Mappers.getMapper(TypeMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"type_id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"type_name\"))")
    TypeEntity toTypeEntity(ResultSet resultSet) throws SQLException;
    
    TypeDTO toTypeDTO(TypeEntity TypeEntity);       
    TypeListWeb toTypeDetailWeb(TypeDTO TypeDTO);
    Type toType(TypeDTO TypeDTO);
    TypeDTO toTypeDTO(Type Type);
    TypeEntity toTypeEntity(TypeDTO TypeDTO);
    TypeDTO toTypeDTO(TypeCreateWeb typeCreateWeb);
    TypeListWeb toTypeListWeb(TypeDTO TypeDTO);
    
    
}
