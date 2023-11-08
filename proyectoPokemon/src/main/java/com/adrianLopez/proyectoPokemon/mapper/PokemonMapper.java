package com.adrianLopez.proyectoPokemon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonListWeb;
import com.adrianLopez.proyectoPokemon.controller.model.slotPokemon.SlotPokemonCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.slotPokemon.SlotPokemonWeb;
import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;
import com.adrianLopez.proyectoPokemon.peristence.model.SlotPokemonEntity;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    PokemonMapper mapper = Mappers.getMapper(PokemonMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"pok_id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"pok_name\"))")
    @Mapping(target = "height", expression = "java(resultSet.getInt(\"pok_height\"))")
    @Mapping(target = "weight", expression = "java(resultSet.getInt(\"pok_weight\"))")
    @Mapping(target = "exp", expression = "java(resultSet.getInt(\"pok_base_experience\"))")
    @Mapping(target = "slotPokemonEntities", ignore = true)
    @Mapping(target = "statsEntity", ignore = true)
    PokemonEntity toPokemonEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "statsDTO", expression = "java(StatsMapper.mapper.toStatsDTO(pokemonEntity.getStatsEntity()))")
    @Mapping(target = "slotPokemonDTOs", expression = "java(mapSlotPokemonEntitiesToSlotPokemonDTOs(pokemonEntity.getSlotPokemonEntities()))")
    PokemonDTO toPokemonDTO(PokemonEntity pokemonEntity);

    @Mapping(target = "statsEntity", ignore = true)
    @Mapping(target = "slotPokemonEntities", ignore = true)
    PokemonEntity toPokemonEntity(PokemonDTO pokemonDTO);

    @Named("SlotPokemonEntitiesToSlotPokemonDTOs")
    default List<SlotPokemonDTO> mapSlotPokemonEntitiesToSlotPokemonDTOs(List<SlotPokemonEntity> slotPokemonEntities) {
        if (slotPokemonEntities == null) {
            return null;
        }
        return slotPokemonEntities.stream()
                .map(SlotPokemonMapper.mapper::toSlotPokemonDTO)
                .toList();
    }

    @Named("slotPokemonDTOToSlotPokemonWeb")
    default List<SlotPokemonWeb> mapSlotPokemonDTOToSlotPokemonWeb(List<SlotPokemonDTO> slotPokemonDTOs) {
        return slotPokemonDTOs.stream()
                .map(SlotPokemonMapper.mapper::toSlotPokemonWeb)
                .toList();
    }

    @Named("slotPokemonDTOToSlotPokemon")
    default List<SlotPokemon> mapSlotPokemonDTOToSlotPokemon(List<SlotPokemonDTO> slotPokemonDTOs) {
        return slotPokemonDTOs.stream()
                .map(SlotPokemonMapper.mapper::toSlotPokemon)
                .toList();
    }

    @Named("slotPokemonToSlotPokemonDTOs")
    default List<SlotPokemonDTO> mapSlotPokemonToSlotPokemonDTOs(List<SlotPokemon> slotPokemon) {
        return slotPokemon.stream()
                .map(SlotPokemonMapper.mapper::toSlotPokemonDTO)
                .toList();
    }

    @Named("SlotPokemonCreateWebToSlotPokemonDTOs")
    default List<SlotPokemonDTO> mapSlotPokemonCreateWebToSlotPokemonDTOs(List<SlotPokemonCreateWeb> slotPokemonCreateWebs) {
        if (slotPokemonCreateWebs == null) {
            return null;
        }
        return slotPokemonCreateWebs.stream()
                .map(SlotPokemonMapper.mapper::toSlotPokemonDTO)
                .toList();
    }

    @Mapping(target = "slots", expression = "java(mapSlotPokemonDTOToSlotPokemonWeb(PokemonDTO.getSlotPokemonDTOs()))")
    PokemonListWeb toPokemonListWeb(PokemonDTO PokemonDTO);

    @Mapping(target = "slots", expression = "java(mapSlotPokemonDTOToSlotPokemonWeb(PokemonDTO.getSlotPokemonDTOs()))")
    @Mapping(target = "stats", expression = "java(StatsMapper.mapper.toStatsDetailWeb(PokemonDTO.getStatsDTO()))")
    PokemonDetailWeb toPokemonDetailWeb(PokemonDTO PokemonDTO);

    @Mapping(target = "statsDTO", expression = "java(StatsMapper.mapper.toStatsDTO(pokemonCreateWeb.getStats()))")
    @Mapping(target = "slotPokemonDTOs", expression = "java(mapSlotPokemonCreateWebToSlotPokemonDTOs(pokemonCreateWeb.getSlots()))")
    PokemonDTO toPokemonDTO(PokemonCreateWeb pokemonCreateWeb);

    @Mapping(target = "statsDTO", expression = "java(StatsMapper.mapper.toStatsDTO(pokemon.getStats()))")
    @Mapping(target = "slotPokemonDTOs", expression = "java(mapSlotPokemonToSlotPokemonDTOs(pokemon.getSlotPokemons()))")
    PokemonDTO toPokemonDTO(Pokemon pokemon);

    @Mapping(target = "slotPokemons", expression = "java(mapSlotPokemonDTOToSlotPokemon(PokemonDTO.getSlotPokemonDTOs()))")
    @Mapping(target = "stats", expression = "java(StatsMapper.mapper.toStats(PokemonDTO.getStatsDTO()))")
    Pokemon toPokemon(PokemonDTO PokemonDTO);

}