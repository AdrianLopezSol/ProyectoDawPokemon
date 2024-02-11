package com.adrianLopez.proyectoPokemon.persistance.dao.impl.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.persistance.dao.impl.model.PokemonEntity;

@Component
public interface PokemonJpaRepository extends JpaRepository<PokemonEntity, Integer>{
    
}
