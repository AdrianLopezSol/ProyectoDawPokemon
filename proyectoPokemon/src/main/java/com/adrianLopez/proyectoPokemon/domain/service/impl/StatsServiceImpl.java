package com.adrianLopez.proyectoPokemon.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.common.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.domain.entity.Stats;
import com.adrianLopez.proyectoPokemon.domain.mapper.StatsDomainMapper;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.domain.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;

    @Override
    public StatsDTO find(int id) {
        return StatsDomainMapper
                .mapper
                .toStatsDTO(this.findStats(id));
    }

    private Stats findStats(int id) {
        return statsRepository
                                .find(id)
                                .orElseThrow(
                                        () -> new ResourceNotFoundException("Stats not found with id: " + id)
                                );
    }

    @Override
    public StatsDTO create(StatsDTO statsDTO) {
        return StatsDomainMapper
                .mapper
                .toStatsDTO(
                        statsRepository
                                .save(
                                        StatsDomainMapper
                                                .mapper
                                                .toStats(statsDTO)
                                )
                );
    }

    @Override
    public StatsDTO update(StatsDTO statsDTO) {
        Stats stats = this.findStats(statsDTO.getStats_id());
        StatsDomainMapper.mapper.updateStatsFromStatsDTO(statsDTO, stats);
        return StatsDomainMapper
                .mapper
                .toStatsDTO(
                        statsRepository
                                .save(stats)
                );
    }

    @Override
    public void delete(int id) {
        this.findStats(id);
        statsRepository.delete(id);
    }


}
