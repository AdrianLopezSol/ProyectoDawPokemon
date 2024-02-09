package com.adrianLopez.proyectoPokemon.peristence.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.peristence.dao.StatsDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.impl.jpaRepository.StatsJpaRepository;
import com.adrianLopez.proyectoPokemon.peristence.dao.impl.mapper.StatsEntityMapper;

@Component
public class StatsDAOImpl implements StatsDAO {

    @Autowired
    StatsJpaRepository statsJpaRepository;

    @Override
    public Optional<StatsDTO> find(int id) {
        return Optional.ofNullable(
            StatsEntityMapper
                        .mapper
                        .toStatsDTO(
                                statsJpaRepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public StatsDTO save(StatsDTO statsDTO) {
        return StatsEntityMapper
                .mapper
                .toStatsDTO(
                    statsJpaRepository
                                .save(
                                    StatsEntityMapper
                                                .mapper
                                                .toStatsEntity(statsDTO)
                                )
                );
    }

    @Override
    public void delete(int id) {
        statsJpaRepository.deleteById(id);
    }
    
}
