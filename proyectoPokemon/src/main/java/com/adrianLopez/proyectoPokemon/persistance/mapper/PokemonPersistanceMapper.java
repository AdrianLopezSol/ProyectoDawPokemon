package com.adrianLopez.proyectoPokemon.persistance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;

@Mapper(componentModel = "spring")
public interface PokemonPersistanceMapper {

    PokemonPersistanceMapper mapper = Mappers.getMapper(PokemonPersistanceMapper.class);

    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "slotPokemons", ignore = true)
    Pokemon toPokemon(PokemonDTO pokemonDTO);

    @Mapping(target = "stats", expression = "java(StatsPersistanceMapper.mapper.toStats(pokemonDTO.getStatsDTO()))")
    @Mapping(target = "slotPokemons", expression = "java(SlotPokemonPersistanceMapper.mapper.toSlotPokemons(pokemonDTO.getSlotPokemonDTOs()))")
    Pokemon toPokemonWithStatsAndSlots(PokemonDTO pokemonDTO);

    @Mapping(target = "slotPokemons", expression = "java(SlotPokemonPersistanceMapper.mapper.toSlotPokemons(pokemonDTO.getSlotPokemonDTOs()))")
    @Mapping(target = "stats", ignore = true)
    Pokemon toPokemonWithTypes(PokemonDTO pokemonDTO);

    @Mapping(target = "statsDTO", expression = "java(StatsPersistanceMapper.mapper.toStatsDTO(pokemon.getStats()))")
    @Mapping(target = "slotPokemonDTOs", expression = "java(SlotPokemonPersistanceMapper.mapper.toSlotPokemonDTOs(pokemon.getSlotPokemons()))")
    PokemonDTO toPokemonDTO(Pokemon pokemon);
    
}
