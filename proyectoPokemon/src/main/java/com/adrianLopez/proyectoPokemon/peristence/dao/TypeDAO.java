package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.mapper.TypeMapper;
import com.adrianLopez.proyectoPokemon.peristence.model.TypeEntity;

@Component
public class TypeDAO {
    
    public List<TypeEntity> findAll(Connection connection) {
        List<Object> params = null;
        String sql = "SELECT * FROM types";
        List<TypeEntity> typeEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                TypeEntity typeEntity = TypeMapper.mapper.toTypeEntity(resultSet);
                typeEntities.add(typeEntity);
            }
            return typeEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<TypeEntity> findByPokemonId(Connection connection, int id) {
        List<TypeEntity> typeEntities = new ArrayList<>();
        final String sql = """
            SELECT t.type_name, t.type_id
                FROM types t
                INNER JOIN pokemon_types pt ON t.type_id = pt.type_id
                INNER JOIN pokemon p ON p.pok_id = pt.pok_id
                WHERE p.pok_id = ?
                ORDER BY pt.slot;
            """;
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            while (resultSet.next()) {
                TypeEntity typeEntity = TypeMapper.mapper.toTypeEntity(resultSet);
                typeEntities.add(typeEntity);
            }
            return typeEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

        public Optional<TypeEntity> findByPokemonIdAndSlot(Connection connection, int id, int slot) {
        final String sql = """
            SELECT t.type_name, t.type_id
                FROM types t
                INNER JOIN pokemon_types pt ON t.type_id = pt.type_id
                INNER JOIN pokemon p ON p.pok_id = pt.pok_id
                WHERE p.pok_id = ? AND pt.slot = ?
            """;
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id, slot));
            return Optional.of(resultSet.next()? TypeMapper.mapper.toTypeEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
