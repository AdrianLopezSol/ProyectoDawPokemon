package com.adrianLopez.proyectoPokemon.domain.repository;

import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

public interface SlotPokemonRepository {

    public boolean exists(int id);

    public int insert(SlotPokemonDTO slotPokemonDTO, int id);

    public void update(SlotPokemonDTO slotPokemonDTO, int id);

    public void delete(int id);
    
}
