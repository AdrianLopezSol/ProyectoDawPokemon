package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;

@Component
public class PokemonDAO {

    public List<PokemonEntity> findAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM pokemon";
        if (page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                PokemonEntity pokemonEntity = PokemonMapper.mapper.toPokemonEntity(resultSet);
                pokemonEntities.add(pokemonEntity);
            }
            return pokemonEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PokemonEntity find(Connection connection, int id) {
        final String sql = "SELECT * FROM pokemon WHERE pok_id = ?";
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            resultSet.next();
            PokemonEntity pokemonEntity = PokemonMapper.mapper.toPokemonEntity(resultSet);
            return pokemonEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
        public int getTotalNumberOfRecords(Connection connection) {
            final String SQL = "SELECT COUNT(*) FROM pokemon";
            try {
                ResultSet resultSet = DBUtil.select(connection, SQL, null);
                resultSet.next();
                return resultSet.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException("SQL: " + SQL);
            }
        }
}