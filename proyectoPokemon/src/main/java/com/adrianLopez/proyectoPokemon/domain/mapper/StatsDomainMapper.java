package com.adrianLopez.proyectoPokemon.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.domain.entity.Stats;

@Mapper(componentModel = "spring")
public interface StatsDomainMapper {

    StatsDomainMapper mapper = Mappers.getMapper(StatsDomainMapper.class);

    StatsDTO toStatsDTO(Stats stats);

    Stats toStats(StatsDTO statsDTO);

    void updateStatsFromStatsDTO(StatsDTO statsDTO, @MappingTarget Stats stats);
    
}
