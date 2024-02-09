package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.dto.StatsDTO;

@Component
public interface StatsDAO {

    public Optional<StatsDTO> find(int id);

    public StatsDTO save(StatsDTO statsDTO);

    public void delete(int id);

}
