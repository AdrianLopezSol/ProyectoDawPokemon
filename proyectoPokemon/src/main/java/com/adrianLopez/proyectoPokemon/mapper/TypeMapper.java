package com.adrianLopez.proyectoPokemon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeCreatePokemonWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeUpdateWeb;
import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.peristence.model.TypeEntity;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    TypeMapper mapper = Mappers.getMapper(TypeMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"type_id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"type_name\"))")
    TypeEntity toTypeEntity(ResultSet resultSet) throws SQLException;

    TypeDTO toTypeDTO(TypeCreateWeb typeCreateWeb);

    TypeDetailWeb toTypeDetailWeb(TypeDTO TypeDTO);

    TypeCreatePokemonWeb toTypeCreatePokemonWeb(TypeDTO TypeDTO);

    Type toType(TypeDTO TypeDTO);

    TypeDTO toTypeDTO(Type Type);

    TypeEntity toTypeEntity(TypeDTO TypeDTO);

    @Mapping(target = "name", ignore = true)
    TypeDTO toTypeDTO(TypeCreatePokemonWeb typeCreatePokemonWeb);

    TypeDTO toTypeDTO(TypeEntity typeEntity);

    TypeDTO toTypeDTO(TypeUpdateWeb typeUpdateWeb);

    TypeListWeb toTypeListWeb(TypeDTO TypeDTO);

}
