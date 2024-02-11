package com.adrianLopez.proyectoPokemon.persistance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.Stats;

@Mapper(componentModel = "spring")
public interface StatsPersistanceMapper {

    StatsPersistanceMapper mapper = Mappers.getMapper(StatsPersistanceMapper.class);

    Stats toStats(StatsDTO statsDTO);

    StatsDTO toStatsDTO(Stats stats);
    
}
