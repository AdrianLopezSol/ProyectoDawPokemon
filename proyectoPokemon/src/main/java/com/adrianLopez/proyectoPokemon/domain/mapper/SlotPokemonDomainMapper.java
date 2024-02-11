package com.adrianLopez.proyectoPokemon.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;

@Mapper(componentModel = "spring")
public interface SlotPokemonDomainMapper {

    SlotPokemonDomainMapper mapper = Mappers.getMapper(SlotPokemonDomainMapper.class);

    @Mapping(target = "type", expression = "java(TypeDomainMapper.mapper.toType(slotPokemonDTO.getTypeDTO()))")
    SlotPokemon toSlotPokemon(SlotPokemonDTO slotPokemonDTO);

    @Mapping(target = "typeDTO", source = "type")
    SlotPokemonDTO toSlotPokemonDTO(SlotPokemon slotPokemon);

    List<SlotPokemonDTO> toSlotPokemonDTOs(List<SlotPokemon> slotPokemons);

    List<SlotPokemon> toSlotPokemons(List<SlotPokemonDTO> slotPokemonDTOs);

    void updateSlotPokemonFromSlotPokemonDTO(SlotPokemonDTO slotPokemonDTO, @MappingTarget SlotPokemon slotPokemon);
}