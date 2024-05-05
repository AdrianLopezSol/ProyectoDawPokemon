package com.adrianLopez.proyectoPokemon.persistance.dao.impl.jpaRepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.persistance.dao.impl.model.PokemonEntity;

@Component
public interface PokemonJpaRepository extends JpaRepository<PokemonEntity, Integer> {

    List<PokemonEntity> findBySlotPokemonEntitiesTypeEntityId(int typeId, Pageable pageable);

    List<PokemonEntity> findBySlotPokemonEntitiesTypeEntityId(int typeId);

    List<PokemonEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<PokemonEntity> findByNameContainingIgnoreCase(String name);

}
