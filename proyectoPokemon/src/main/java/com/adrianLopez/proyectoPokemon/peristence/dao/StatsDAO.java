package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;
import com.adrianLopez.proyectoPokemon.peristence.model.StatsEntity;

@Component
public class StatsDAO {

    public Optional<StatsEntity> findByPokemonId(Connection connection, int id) {
        final String sql = """
            SELECT s.*
                FROM base_stats s
                INNER JOIN pokemon p ON p.pok_id = s.pok_id
                WHERE p.pok_id = ?
            """;
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            return Optional.ofNullable(resultSet.next()? StatsMapper.mapper.toStatsEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
