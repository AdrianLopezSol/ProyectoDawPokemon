package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;

@Repository
public interface PokemonRepository {

    public Stream<Pokemon> getAll(Integer page, Integer pageSize);

    public Optional<Pokemon> find(int id);

    public long getTotalNumberOfRecords();

    public boolean exists(int id);

    public Pokemon save(Pokemon pokemon);

    public void delete(int id);

    public void deletePokemonTypes(int id);

    public SlotPokemon savePokemonType(SlotPokemon slotPokemon);

}
