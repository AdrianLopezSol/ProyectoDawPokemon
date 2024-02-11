package com.adrianLopez.proyectoPokemon.persistance.dao.impl.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.model.SlotPokemonEntity;

@Mapper(componentModel = "spring")
public interface SlotPokemonEntityMapper {

    SlotPokemonEntityMapper mapper = Mappers.getMapper(SlotPokemonEntityMapper.class);

    @Mapping(target = "typeDTO", source = "typeEntity")
    SlotPokemonDTO toSlotPokemonDTO(SlotPokemonEntity slotPokemonEntity);

    List<SlotPokemonDTO> toSlotPokemonDTOs(List<SlotPokemonEntity> slotPokemonEntities);

    @Mapping(target = "typeEntity", source = "typeDTO")
    SlotPokemonEntity toSlotPokemonEntity(SlotPokemonDTO slotPokemonDTO);

    List<SlotPokemonEntity> toSlotPokemonEntities(List<SlotPokemonDTO> slotPokemonDTOs);

}
