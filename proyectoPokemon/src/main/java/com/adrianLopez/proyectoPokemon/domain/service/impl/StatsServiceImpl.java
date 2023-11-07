package com.adrianLopez.proyectoPokemon.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.domain.service.StatsService;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;

@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    StatsRepository statsRepository;

    @Override
    public int create(StatsDTO statsDTO) {
        return statsRepository.insert(statsDTO);
    }

    @Override
    public void update(StatsDTO statsDTO) {
        statsRepository.find(statsDTO.getPok_id()).orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + statsDTO.getPok_id()));
        statsRepository.update(statsDTO);
    }

    @Override
    public void delete(int id) {
        statsRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
        statsRepository.delete(id);
    }

    @Override
    public StatsDTO find(int id) {
        return statsRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
    }
    
}
