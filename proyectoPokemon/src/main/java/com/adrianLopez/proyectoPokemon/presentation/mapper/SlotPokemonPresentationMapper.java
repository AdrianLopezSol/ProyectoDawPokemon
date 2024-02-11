package com.adrianLopez.proyectoPokemon.presentation.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.presentation.request.SlotPokemonRequest;
import com.adrianLopez.proyectoPokemon.presentation.response.SlotPokemonResponse;

@Mapper(componentModel = "spring")
public interface SlotPokemonPresentationMapper {

    SlotPokemonPresentationMapper mapper = Mappers.getMapper(SlotPokemonPresentationMapper.class);

    @Mapping(target = "typeResponse", expression = "java(TypePresentationMapper.mapper.toTypeResponse(slotPokemonDTO.getTypeDTO()))")
    SlotPokemonResponse toSlotPokemonResponse(SlotPokemonDTO slotPokemonDTO);

    List<SlotPokemonResponse> toSlotPokemonResponses(List<SlotPokemonDTO> slotPokemonDTOs);

    @Mapping(target = "typeDTO", source = "typeRequest")
    SlotPokemonDTO toSlotPokemonDTO(SlotPokemonRequest slotPokemonRequest);

    List<SlotPokemonDTO> toSlotPokemonDTOs(List<SlotPokemonRequest> slotPokemonRequests);
}
