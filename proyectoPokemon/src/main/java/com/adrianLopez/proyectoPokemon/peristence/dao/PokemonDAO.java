package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            throw new RuntimeException();
        }
    }

    public Optional<PokemonEntity> find(Connection connection, int id) {
        final String sql = "SELECT * FROM pokemon WHERE pok_id = ?";
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            return Optional.ofNullable(resultSet.next() ? PokemonMapper.mapper.toPokemonEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean exists(Connection connection, int id) {
        final String sql = "SELECT * FROM pokemon WHERE pok_id = ?";
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            if (resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
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

    public int insert(Connection connection, PokemonEntity pokemonEntity) {
        final String SQL = "INSERT INTO pokemon (pok_name, pok_height, pok_weight, pok_base_experience) VALUES (?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(pokemonEntity.getName());
        params.add(pokemonEntity.getHeight());
        params.add(pokemonEntity.getWeight());
        params.add(pokemonEntity.getExp());
        return DBUtil.insert(connection, SQL, params);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM pokemon WHERE pok_id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

    public void update(Connection connection, PokemonEntity pokemonEntity) {
        final String SQL = """
            UPDATE pokemon SET pok_name = ?, pok_height = ?, pok_weight = ?, pok_base_experience = ? WHERE pok_id = ?
            """;
        List<Object> params = new ArrayList<>();
        params.add(pokemonEntity.getName());
        params.add(pokemonEntity.getHeight());
        params.add(pokemonEntity.getWeight());
        params.add(pokemonEntity.getExp());
        params.add(pokemonEntity.getId());
        DBUtil.update(connection, SQL, params);
    }

}