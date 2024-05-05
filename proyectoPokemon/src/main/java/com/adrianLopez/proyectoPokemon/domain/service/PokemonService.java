package com.adrianLopez.proyectoPokemon.domain.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;

@Service
public interface PokemonService {

     public Stream<PokemonDTO> getAll(Integer page, Integer pageSize);

     public Stream<PokemonDTO> getByTypeId(Integer page, Integer pageSize, int typeId);

     public Stream<PokemonDTO> getByNameLike(Integer page, Integer pageSize, String name);

     public Stream<PokemonDTO> getByTypeId(int typeId);

     public Stream<PokemonDTO> getByNameLike(String name);

     public Stream<PokemonDTO> getAll();

     public PokemonDTO find(int id);

     public PokemonDTO create(PokemonDTO pokemonDTO);

     public void delete(int id);

     public PokemonDTO update(PokemonDTO pokemonDTO);

     long getTotalNumberOfRecords();

     public PokemonDTO addPokemonType(SlotPokemonDTO slotPokemonDTO, int id);

     public PokemonDTO updatePokemonType(SlotPokemonDTO slotPokemonDTO, int id);

     public void deletePokemonTypes(int id);
    
}
