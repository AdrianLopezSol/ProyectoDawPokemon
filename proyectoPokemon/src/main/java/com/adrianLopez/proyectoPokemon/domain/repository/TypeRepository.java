package com.adrianLopez.proyectoPokemon.domain.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Type;

@Repository
public interface TypeRepository {

    public Stream<Type> findAll();

    public Optional<Type> findByPokemonTypeId(int id);

    public void delete(int id);

    public Type save(Type type);

    public Optional<Type> find(int id);

}