package com.adrianLopez.proyectoPokemon.persistance.repositoryImpl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.persistance.dao.PokemonDAO;
import com.adrianLopez.proyectoPokemon.persistance.dao.SlotPokemonDAO;
import com.adrianLopez.proyectoPokemon.persistance.mapper.PokemonPersistanceMapper;
import com.adrianLopez.proyectoPokemon.persistance.mapper.SlotPokemonPersistanceMapper;

import jakarta.transaction.Transactional;

@Repository
public class PokemonRepositoryImpl implements PokemonRepository {

    @Autowired
    private PokemonDAO pokemonDAO;

    @Autowired
    private SlotPokemonDAO slotPokemonDAO;

    @Override
    public Stream<Pokemon> getAll(Integer page, Integer pageSize) {
        return pokemonDAO
                .findAll(page, pageSize)
                .map(pokemonDTO -> PokemonPersistanceMapper.mapper.toPokemonWithTypes(pokemonDTO));
    }

    @Override
    public Stream<Pokemon> getByTypeId(Integer page, Integer pageSize, int typeId) {
        return pokemonDAO
                .findByTypeId(page, pageSize, typeId)
                .map(pokemonDTO -> PokemonPersistanceMapper.mapper.toPokemonWithTypes(pokemonDTO));
    }

    @Override
    public Stream<Pokemon> getByNameLike(Integer page, Integer pageSize, String name) {
        return pokemonDAO
                .findByNameLike(page, pageSize, name)
                .map(pokemonDTO -> PokemonPersistanceMapper.mapper.toPokemonWithTypes(pokemonDTO));
    }

    @Override
    public Optional<Pokemon> find(int id) {
        return Optional.ofNullable(
                PokemonPersistanceMapper.mapper
                        .toPokemonWithStatsAndSlots(
                                pokemonDAO
                                        .find(id)
                                        .orElse(null)));
    }

    @Override
    public long getTotalNumberOfRecords() {
        return pokemonDAO.count();
    }

    @Override
    @Transactional
    public Pokemon save(Pokemon pokemon) {
        return PokemonPersistanceMapper.mapper
                .toPokemonWithStatsAndSlots(
                        pokemonDAO
                                .save(
                                        PokemonPersistanceMapper.mapper
                                                .toPokemonDTO(pokemon)));
    }

    @Override
    @Transactional
    public void delete(int id) {
        pokemonDAO.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return pokemonDAO.existsById(id);
    }

    public void deletePokemonTypes(int id){
        slotPokemonDAO.delete(id);
    }

    @Override
    @Transactional
    public SlotPokemon savePokemonType(SlotPokemon slotPokemon) {
        return SlotPokemonPersistanceMapper.mapper
                .toSlotPokemon(
                        slotPokemonDAO
                                .save(
                                        SlotPokemonPersistanceMapper.mapper
                                                .toSlotPokemonDTO(slotPokemon)));
    }


}
