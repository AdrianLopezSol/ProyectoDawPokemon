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
        String sql = """
            SELECT pt.type_id, t.type_name, p.*
            FROM pokemon_types pt
            INNER JOIN types t ON t.type_id = pt.type_id
            INNER JOIN pokemon p ON p.pok_id = pt.pok_id
            ORDER BY pok_id;
        """;
        if (page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            resultSet.next();
            while (true) {
                PokemonEntity pokemonEntity = PokemonMapper.mapper.toPokemonEntity(resultSet);
                pokemonEntity.setType_id1(resultSet.getInt("type_id"));
                if(resultSet.next()) {
                    if (resultSet.getInt("pok_id") == pokemonEntity.getId()){
                        System.out.println(resultSet.getInt("type_id"));
                        pokemonEntity.setType_id2(resultSet.getInt("type_id"));
                        System.out.println(pokemonEntity.getType_id1());
                        pokemonEntities.add(pokemonEntity);
                        if (!resultSet.next()){
                            return pokemonEntities;
                        }
                    }
                    else{pokemonEntities.add(pokemonEntity);}
                }
                else {
                    return pokemonEntities;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<PokemonEntity> find(Connection connection, int id) {
        final String SQL = """
            SELECT pt.type_id, t.type_name, p.*
            FROM pokemon_types pt
            INNER JOIN types t ON t.type_id = pt.type_id
            INNER JOIN pokemon p ON p.pok_id = pt.pok_id
            WHERE p.pok_id = ? ORDER BY pok_id;
        """;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? PokemonMapper.mapper.toPokemonEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
