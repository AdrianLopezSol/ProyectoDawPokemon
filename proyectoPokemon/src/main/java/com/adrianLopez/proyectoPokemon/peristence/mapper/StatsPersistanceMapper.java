package com.adrianLopez.proyectoPokemon.peristence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.domain.entity.Stats;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;

@Mapper(componentModel = "spring")
public interface StatsPersistanceMapper {

    StatsPersistanceMapper mapper = Mappers.getMapper(StatsPersistanceMapper.class);

    Stats toStats(StatsDTO statsDTO);

    StatsDTO toStatsDTO(Stats stats);
    
}
