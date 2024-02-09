package com.adrianLopez.proyectoPokemon.peristence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

@Mapper(componentModel = "spring")
public interface SlotPokemonPersistanceMapper {

    SlotPokemonPersistanceMapper mapper = Mappers.getMapper(SlotPokemonPersistanceMapper.class);

    @Mapping(target = "type", expression = "java(TypePersistanceMapper.mapper.toType(slotPokemonDTO.getTypeDTO()))")
    SlotPokemon toSlotPokemon(SlotPokemonDTO slotPokemonDTO);

    List<SlotPokemon> SlotPokemons(List<SlotPokemonDTO> slotPokemonDTOs);

    @Mapping(target = "typeDTO", expression = "java(TypePersistanceMapper.mapper.toTypeDTO(slotPokemon.getType()))")
    SlotPokemonDTO toSlotPokemonDTO(SlotPokemon slotPokemon);

    List<SlotPokemonDTO> toSlotPokemonDTOs(List<SlotPokemon> slotPokemons);
    
}
