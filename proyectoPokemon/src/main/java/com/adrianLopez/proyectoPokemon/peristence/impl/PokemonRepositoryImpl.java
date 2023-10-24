package com.adrianLopez.proyectoPokemon.peristence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.exception.DBConnectionException;
import com.adrianLopez.proyectoPokemon.exception.SQLStatementException;

@Repository
public class PokemonRepositoryImpl implements PokemonRepository {

    @Override
    public List<Pokemon> getAll() {
        final String SQL = "SELECT * FROM pokemon";
        List<Pokemon> Pokemons = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                Pokemons.add(
                        new Pokemon(
                                resultSet.getString("pok_name"),
                                resultSet.getInt("pok_id")
                        )
                );
            }
            DBUtil.close(connection);
            return Pokemons;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }
 
    @Override
    public Optional<Pokemon> find(int id) {
        final String SQL = "SELECT * FROM pokemon WHERE pok_id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new Pokemon(
                                resultSet.getString("pok_name"),
                                resultSet.getInt("pok_id"),
                                resultSet.getDouble("pok_height"),
                                resultSet.getDouble("pok_weight"),
                                resultSet.getInt("pok_base_experience")
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
}
