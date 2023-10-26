package com.adrianLopez.proyectoPokemon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonListWeb;
import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    PokemonMapper mapper = Mappers.getMapper(PokemonMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"pok_id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"pok_name\"))")
    @Mapping(target = "height", expression = "java(resultSet.getDouble(\"pok_height\"))")
    @Mapping(target = "weight", expression = "java(resultSet.getDouble(\"pok_weight\"))")
    @Mapping(target = "exp", expression = "java(resultSet.getInt(\"pok_base_experience\"))")
    PokemonEntity toPokemonEntity(ResultSet resultSet) throws SQLException;
    
    PokemonDTO toPokemonDTO(PokemonEntity PokemonEntity);       
    PokemonListWeb toPokemonListWeb(PokemonDTO PokemonDTO);
    PokemonDetailWeb toPokemonDetailWeb(PokemonDTO PokemonDTO);
    Pokemon toPokemon(PokemonDTO PokemonDTO);
    PokemonDTO toPokemonDTO(Pokemon Pokemon);
    PokemonEntity toPokemonEntity(PokemonDTO PokemonDTO);
    
}