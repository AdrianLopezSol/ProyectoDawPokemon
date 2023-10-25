package com.adrianLopez.proyectoPokemon.peristence.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;
import com.adrianLopez.proyectoPokemon.peristence.dao.PokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;

@Repository
public class PokemonRepositoryImpl implements PokemonRepository {

    @Autowired
    PokemonDAO pokemonDAO;
    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<PokemonEntity> PokemonEntities = pokemonDAO.findAll(connection, page, pageSize);
            System.out.println("Before entities to dto map");
            List<PokemonDTO> PokemonDTOs = PokemonEntities.stream()
                    .map(PokemonMapper.mapper::toPokemonDTO)
                    .toList();
            System.out.println("Reached repo");
            return PokemonDTOs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PokemonDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<PokemonEntity> PokemonEntity = pokemonDAO.find(connection, id);
            if(PokemonEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(PokemonMapper.mapper.toPokemonDTO(PokemonEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
