package com.adrianLopez.proyectoPokemon.peristence.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;
import com.adrianLopez.proyectoPokemon.peristence.dao.StatsDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.StatsEntity;

@Repository
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    StatsDAO statsDAO;

    @Override
    public StatsDTO findByPokemonId(int id) {
        try (Connection connection= DBUtil.open(true)){
            StatsEntity statsEntity = statsDAO.findByPokemonId(connection, id);
            return StatsMapper.mapper.toStatsDTO(statsEntity);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
}
