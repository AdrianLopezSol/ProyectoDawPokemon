package com.adrianLopez.proyectoPokemon.domain.service;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;

@Service
public interface StatsService {

    public StatsDTO create(StatsDTO statsDTO);

    public StatsDTO update(StatsDTO statsDTO);

    public void delete(int id);

    public StatsDTO find(int id);

}
