package com.adrianLopez.proyectoPokemon.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.presentation.request.PokemonRequest;
import com.adrianLopez.proyectoPokemon.presentation.response.PokemonResponse;


@Mapper(componentModel = "spring")
public interface PokemonPresentationMapper {

    PokemonPresentationMapper mapper = Mappers.getMapper(PokemonPresentationMapper.class);

    @Mapping(target = "link", ignore = true )
    @Mapping(target = "statsResponse", source = "statsDTO")
    @Mapping(target = "slotPokemonResponses", expression = "java(SlotPokemonPresentationMapper.mapper.toSlotPokemonResponses(pokemonDTO.getSlotPokemonDTOs()))")
    PokemonResponse toPokemonResponse(PokemonDTO pokemonDTO);

    @Mapping(target = "statsDTO", source = "statsRequest")
    @Mapping(target = "slotPokemonDTOs", expression = "java(SlotPokemonPresentationMapper.mapper.toSlotPokemonDTOs(pokemonRequest.getSlotPokemonRequests()))")
    PokemonDTO toPokemonDTO(PokemonRequest pokemonRequest);

}