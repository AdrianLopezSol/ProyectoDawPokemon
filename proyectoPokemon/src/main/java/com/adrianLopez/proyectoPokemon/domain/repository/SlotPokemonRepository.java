package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

@Repository
public interface SlotPokemonRepository {

    public boolean exists(int id);

    public int insert(SlotPokemonDTO slotPokemonDTO, int id);

    public void update(List<SlotPokemonDTO> slotPokemonDTOs, int id);

    public void delete(int id);
    
}
