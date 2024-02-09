package com.adrianLopez.proyectoPokemon.peristence.dao.impl.jpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.peristence.dao.impl.model.TypeEntity;

@Component
public interface TypeJpaRepository extends JpaRepository<TypeEntity, Integer>{

    @Query("SELECT t FROM TypeEntity t, SlotPokemonEntity p WHERE p.pok_id = :id AND p.slot = :idSlot")
    Optional<TypeEntity> findByIdAndSlot(@Param("id") int id, @Param("idSlot") int slot);
    
}
