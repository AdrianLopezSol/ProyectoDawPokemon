package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

@Service
public interface PokemonService {

     public List<PokemonDTO> getAll(Integer page, Integer pageSize);

     public List<PokemonDTO> getAll();

     public PokemonDTO find(int id);

     public int create(PokemonDTO pokemonDTO);

     public void delete(int id);

     public void update(PokemonDTO pokemonDTO);

     int getTotalNumberOfRecords();

     public int createPokemonType(SlotPokemonDTO slotPokemonDTO, int id);

     public void updatePokemonType(SlotPokemonDTO slotPokemonDTO, int id);

     public void deletePokemonTypes(int id);
    
}
