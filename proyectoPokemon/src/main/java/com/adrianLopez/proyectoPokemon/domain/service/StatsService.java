package com.adrianLopez.proyectoPokemon.domain.service;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.dto.StatsDTO;

@Service
public interface StatsService {

    public int create(StatsDTO statsDTO, int id);

    public void update(StatsDTO statsDTO, int id);

    public void delete(int id);

    public StatsDTO find(int id);

}
