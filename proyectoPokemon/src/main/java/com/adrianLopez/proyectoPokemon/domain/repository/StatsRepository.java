package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Stats;

@Repository
public interface StatsRepository {

    public Optional<Stats> find(int id);

    public Stats save(Stats stats);

    public void delete(int id);

}