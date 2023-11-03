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
import com.adrianLopez.proyectoPokemon.peristence.dao.SlotPokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.StatsDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.TypeDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;

@Repository
public class PokemonRepositoryImpl implements PokemonRepository {

    @Autowired
    PokemonDAO pokemonDAO;

    @Autowired
    StatsDAO statsDAO;

    @Autowired
    TypeDAO typeDAO;

    @Autowired
    SlotPokemonDAO slotPokemonDAO;
    
    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<PokemonEntity> PokemonEntities = pokemonDAO.findAll(connection, page, pageSize);
            List<PokemonDTO> PokemonDTOs = PokemonEntities.stream()
                    .map(pokemonEntity -> pokemonEntity.getSlotPokemonEntities(connection, slotPokemonDAO))
                    .map(PokemonMapper.mapper::toPokemonDTO)
                    .toList();
            return PokemonDTOs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PokemonDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            PokemonEntity pokemonEntity = pokemonDAO.find(connection, id).get();
            if(pokemonEntity != null) {
                pokemonEntity.getStatsEntity(connection, statsDAO);
                pokemonEntity.getSlotPokemonEntities(connection, slotPokemonDAO).forEach(SlotPokemonEntity -> SlotPokemonEntity.getTypeEntity(connection, typeDAO, pokemonEntity.getId()));
            }
            Optional<PokemonDTO> response = Optional.ofNullable(PokemonMapper.mapper.toPokemonDTO(pokemonEntity));
            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)) {
            return pokemonDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
