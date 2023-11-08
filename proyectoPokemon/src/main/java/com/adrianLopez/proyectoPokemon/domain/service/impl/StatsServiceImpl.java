package com.adrianLopez.proyectoPokemon.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.Stats;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.domain.service.StatsService;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public int create(StatsDTO statsDTO, int id) {
        Stats stats = StatsMapper.mapper.toStats(statsDTO);
        if (!pokemonRepository.exists(id)){
            throw new ResourceNotFoundException("El pokemon con id: " + id + " no existe");
        }
        if (statsRepository.find(id).isPresent()){
            throw new ResourceNotFoundException("Las estadisticas del pokemon con id: " + id + " ya existen");
        }
        return statsRepository.insert(StatsMapper.mapper.toStatsDTO(stats), id);
    }

    @Override
    public void update(StatsDTO statsDTO, int id) {
        Stats stats = StatsMapper.mapper.toStats(statsDTO);
        statsRepository.find(id).orElseThrow(
                () -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
        statsRepository.update(StatsMapper.mapper.toStatsDTO(stats), id);
    }

    @Override
    public void delete(int id) {
        statsRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
        statsRepository.delete(id);
    }

    @Override
    public StatsDTO find(int id) {
        Stats stats = StatsMapper.mapper.toStats(statsRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id)));
        return StatsMapper.mapper.toStatsDTO(stats);
    }

}
