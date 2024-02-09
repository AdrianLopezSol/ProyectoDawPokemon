package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

@Component
public interface SlotPokemonDAO {

    public Stream<SlotPokemonDTO> findByPokemonId(int id);
    
    public SlotPokemonDTO save(SlotPokemonDTO slotPokemonDTO);

    public void delete(int id);

}
