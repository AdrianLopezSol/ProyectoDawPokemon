package com.adrianLopez.proyectoPokemon.domain.service;

import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

public interface SlotPokemonService {

    public int create(SlotPokemonDTO SlotPokemonDTO, int id);

    public void update(SlotPokemonDTO SlotPokemonDTO, int id);

    public void delete(int id);
    
}
