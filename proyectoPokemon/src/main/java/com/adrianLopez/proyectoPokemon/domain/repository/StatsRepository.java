package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.StatsDTO;

@Repository
public interface StatsRepository {

    public Optional<StatsDTO> find(int id);

    public int insert(StatsDTO statsDTO);

    public void update(StatsDTO statsDTO);

    public void delete(int id);

}