package com.adrianLopez.proyectoPokemon.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.domain.service.StatsService;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public int create(StatsDTO statsDTO, int id) {
        if (!pokemonRepository.exists(id)){
            throw new ResourceNotFoundException("Pokemon no encontrado con id: " + id);
        }
        return statsRepository.insert(statsDTO, id);
    }

    @Override
    public void update(StatsDTO statsDTO, int id) {
        statsRepository.find(id).orElseThrow(
                () -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
        statsRepository.update(statsDTO, id);
    }

    @Override
    public void delete(int id) {
        statsRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
        statsRepository.delete(id);
    }

    @Override
    public StatsDTO find(int id) {
        return statsRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadisticas no encontradas con id: " + id));
    }

}
