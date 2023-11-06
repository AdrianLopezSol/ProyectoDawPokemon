package com.adrianLopez.proyectoPokemon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.controller.model.slotPokemon.SlotPokemonWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.peristence.model.SlotPokemonEntity;
import com.adrianLopez.proyectoPokemon.peristence.model.TypeEntity;

@Mapper(componentModel = "spring")
public interface SlotPokemonMapper {

    SlotPokemonMapper mapper = Mappers.getMapper(SlotPokemonMapper.class);

    @Mapping(target = "typeDTO", expression = "java(mapTypeEntityToTypeDTO(slotPokemonEntity.getTypeEntity()))")
    SlotPokemonDTO toSlotPokemonDTO(SlotPokemonEntity slotPokemonEntity);


    @Mapping(target = "slot", expression = "java(resultSet.getInt(\"slot\"))")
    @Mapping(target = "typeEntity", ignore = true)
    SlotPokemonEntity toSlotPokemonEntity(ResultSet resultSet) throws SQLException;

    @Named("TypeEntityToTypeDTO")
    default TypeDTO mapTypeEntityToTypeDTO(TypeEntity typeEntity) {
        return TypeMapper.mapper.toTypeDTO(typeEntity);
    }

    @Mapping(target = "type", expression = "java(mapTypeDTOtoTypeListWeb(slotPokemonDTO.getTypeDTO()))")
    SlotPokemonWeb toSlotPokemonWeb(SlotPokemonDTO slotPokemonDTO);

    @Named("TypeDTOtoTypeListWeb")
    default TypeListWeb mapTypeDTOtoTypeListWeb(TypeDTO typeDTO) {
        return TypeMapper.mapper.toTypeListWeb(typeDTO);
    }

}
