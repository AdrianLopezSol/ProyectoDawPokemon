package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;

@Component
public class PokemonDAO {

    public List<PokemonEntity> findAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = """
            SELECT
                GROUP_CONCAT(pt.type_id) AS type_ids,
                MIN(p.pok_id) AS pok_id,
                MIN(p.pok_name) AS pok_name,
                MIN(p.pok_weight) AS pok_weight,
                MIN(p.pok_height) AS pok_height,
                MIN(p.pok_base_experience) AS pok_base_experience
            FROM pokemon_types pt
            INNER JOIN pokemon p ON p.pok_id = pt.pok_id
            GROUP BY p.pok_id, p.pok_name, p.pok_weight, p.pok_height, p.pok_base_experience
            ORDER BY p.pok_id
                """;
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
                var typeIds = Arrays
                        .stream(resultSet.getString("type_ids").split(","))
                        .map(typeid -> Integer.parseInt(typeid))
                        .toArray(Integer[]::new);
                pokemonEntity.setTypes(typeIds);
                pokemonEntities.add(pokemonEntity);
            }
            return pokemonEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PokemonEntity find(Connection connection, int id) {
        final String sql = """
                    SELECT
                        GROUP_CONCAT(pt.type_id) AS type_ids,
                        MIN(p.pok_id) AS pok_id,
                        MIN(p.pok_name) AS pok_name,
                        MIN(p.pok_weight) AS pok_weight,
                        MIN(p.pok_height) AS pok_height,
                        MIN(p.pok_base_experience) AS pok_base_experience
                    FROM pokemon_types pt
                    INNER JOIN pokemon p ON p.pok_id = pt.pok_id
                    WHERE p.pok_id = ?
                    GROUP BY p.pok_id
                    ORDER BY p.pok_id;
                """;
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            resultSet.next();
            PokemonEntity pokemonEntity = PokemonMapper.mapper.toPokemonEntity(resultSet);
            var typeIds = Arrays
                    .stream(resultSet.getString("type_ids").split(","))
                    .map(typeid -> Integer.parseInt(typeid))
                    .toArray(Integer[]::new);
            pokemonEntity.setTypes(typeIds);
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