package com.adrianLopez.proyectoPokemon.persistance.dao.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.model.StatsEntity;

@Mapper(componentModel = "spring")
public interface StatsEntityMapper {

    StatsEntityMapper mapper = Mappers.getMapper(StatsEntityMapper.class);

    StatsDTO toStatsDTO(StatsEntity statsEntity);

    StatsEntity toStatsEntity(StatsDTO statsDTO);
    
}
