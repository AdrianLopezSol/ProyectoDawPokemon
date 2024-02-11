package com.adrianLopez.proyectoPokemon.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;

@Mapper(componentModel = "spring")
public interface PokemonDomainMapper {

    PokemonDomainMapper mapper = Mappers.getMapper(PokemonDomainMapper.class);

    @Mapping(target = "statsDTO", source = "stats")
    @Mapping(target = "slotPokemonDTOs", expression = "java(SlotPokemonDomainMapper.mapper.toSlotPokemonDTOs(pokemon.getSlotPokemons()))")
    PokemonDTO toPokemonDTO(Pokemon pokemon);

    @Mapping(target = "stats", source = "statsDTO")
    @Mapping(target = "slotPokemons", expression = "java(SlotPokemonDomainMapper.mapper.toSlotPokemons(pokemonDTO.getSlotPokemonDTOs()))")
    Pokemon toPokemon(PokemonDTO pokemonDTO);

    void updatePokemonFromPokemonDTO(PokemonDTO pokemonDTO, @MappingTarget Pokemon pokemon);
}
