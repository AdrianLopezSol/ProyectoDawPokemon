package com.adrianLopez.proyectoPokemon.persistance.dao.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.model.PokemonEntity;

@Mapper(componentModel = "spring")
public interface PokemonEntityMapper {

    PokemonEntityMapper mapper = Mappers.getMapper(PokemonEntityMapper.class);

    @Mapping(target = "statsDTO", ignore = true)
    @Mapping(target = "slotPokemonDTOs", ignore = true)
    PokemonDTO toPokemonDTO(PokemonEntity pokemonEntity);

    @Mapping(target = "statsDTO", expression = "java(StatsEntityMapper.mapper.toStatsDTO(pokemonEntity.getStatsEntity()))")
    @Mapping(target = "slotPokemonDTOs", expression = "java(SlotPokemonEntityMapper.mapper.toSlotPokemonDTOs(pokemonEntity.getSlotPokemonEntities()))")
    PokemonDTO toPokemonDtoWithStatsAndPokemonTypes(PokemonEntity pokemonEntity);

    @Mapping(target = "slotPokemonDTOs", expression = "java(SlotPokemonEntityMapper.mapper.toSlotPokemonDTOs(pokemonEntity.getSlotPokemonEntities()))")
    @Mapping(target = "statsDTO", ignore = true)
    PokemonDTO toPokemonDtoWithTypes(PokemonEntity pokemonEntity);

    @Mapping(target = "statsEntity", source = "statsDTO")
    @Mapping(target = "slotPokemonEntities", expression = "java(SlotPokemonEntityMapper.mapper.toSlotPokemonEntities(pokemonDTO.getSlotPokemonDTOs()))")
    PokemonEntity toPokemonEntity(PokemonDTO pokemonDTO);

}
