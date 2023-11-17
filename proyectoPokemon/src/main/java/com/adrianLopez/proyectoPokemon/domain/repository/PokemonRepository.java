package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;

@Repository
public interface PokemonRepository {

    public List<PokemonDTO> getAll(Integer page, Integer pageSize);

    public Optional<PokemonDTO> find(int id);

    public int getTotalNumberOfRecords();

    public boolean exists(int id);

    public int insert(PokemonDTO pokemonDTO);

    public void delete(int id);

    public void update(PokemonDTO pokemonDTO);

    public void deletePokemonType(int id);

    public void updatePokemonType(SlotPokemonDTO slotPokemonDTO, int id);

    public int insertPokemonType(SlotPokemonDTO slotPokemonDTO, int id);

}
