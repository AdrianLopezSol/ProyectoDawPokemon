package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;
import com.adrianLopez.proyectoPokemon.peristence.model.SlotPokemonEntity;

@Component
public class SlotPokemonDAO {

    public List<SlotPokemonEntity> findByPokemonId(Connection connection, int id) {
        List<SlotPokemonEntity> slotPokemonEntities = new ArrayList<>();
        final String SQL = """
                    SELECT slot FROM pokemon_types WHERE pok_id = ?
                """;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (!resultSet.next()) {
                return null;
            }
            do {
                slotPokemonEntities.add(SlotPokemonMapper.mapper.toSlotPokemonEntity(resultSet));
            } while (resultSet.next());
            return slotPokemonEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
