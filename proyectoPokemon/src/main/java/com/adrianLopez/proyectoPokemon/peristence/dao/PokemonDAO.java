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
        String sql = "SELECT * FROM Pokemon";
        if (page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<PokemonEntity> PokemonEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                PokemonEntities.add(PokemonMapper.mapper.toPokemonEntity(resultSet));
            }
            return PokemonEntities;
        } catch (SQLException e) {
            System.out.println("SQL");
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public Optional<PokemonEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM pokemon WHERE pok_id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? PokemonMapper.mapper.toPokemonEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}