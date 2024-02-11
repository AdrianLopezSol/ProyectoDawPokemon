package com.adrianLopez.proyectoPokemon.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.presentation.request.StatsRequest;
import com.adrianLopez.proyectoPokemon.presentation.response.StatsResponse;

@Mapper(componentModel = "spring")
public interface StatsPresentationMapper {

    StatsPresentationMapper mapper = Mappers.getMapper(StatsPresentationMapper.class);

    @Mapping(target = "link", ignore = true )
    StatsResponse toStatsResponse(StatsDTO statsDTO);

    StatsDTO toStatsDTO(StatsRequest statsRequest);
}
