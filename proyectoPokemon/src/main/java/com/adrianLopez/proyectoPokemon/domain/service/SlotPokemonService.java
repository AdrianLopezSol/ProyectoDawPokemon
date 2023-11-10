package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

@Service
public interface SlotPokemonService {

    public int create(SlotPokemonDTO slotPokemonDTO, int id);

    public void update(List<SlotPokemonDTO> SlotPokemonDTO, int id);

    public void delete(int id);
    
}
