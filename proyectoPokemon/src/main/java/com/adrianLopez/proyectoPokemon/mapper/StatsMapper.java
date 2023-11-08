package com.adrianLopez.proyectoPokemon.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsUpdateWeb;
import com.adrianLopez.proyectoPokemon.domain.entity.Stats;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.peristence.model.StatsEntity;

@Mapper(componentModel = "spring")
public interface StatsMapper {

    StatsMapper mapper = Mappers.getMapper(StatsMapper.class);

    @Mapping(target = "hp", expression = "java(resultSet.getInt(\"b_hp\"))")
    @Mapping(target = "atk", expression = "java(resultSet.getInt(\"b_atk\"))")
    @Mapping(target = "def", expression = "java(resultSet.getInt(\"b_def\"))")
    @Mapping(target = "sp_atk", expression = "java(resultSet.getInt(\"b_sp_atk\"))")
    @Mapping(target = "sp_def", expression = "java(resultSet.getInt(\"b_sp_def\"))")
    @Mapping(target = "speed", expression = "java(resultSet.getInt(\"b_speed\"))")
    StatsEntity toStatsEntity(ResultSet resultSet) throws SQLException;

    StatsDTO toStatsDTO(StatsEntity StatsEntity);

    Stats toStats(StatsDTO StatsDTO);

    StatsDTO toStatsDTO(Stats Stats);

    StatsDTO toStatsDTO(StatsCreateWeb statsCreateWeb);

    StatsDTO toStatsDTO(StatsUpdateWeb statsUpdateWeb);

    StatsEntity toStatsEntity(StatsDTO StatsDTO);

    StatsDetailWeb toStatsDetailWeb(StatsDTO StatsDTO);

}
