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

    public void update(Connection connection, SlotPokemonEntity slotPokemonEntity, int pok_id) {
        final String SQL = "UPDATE pokemon_types SET type_id = ? WHERE pok_id = ? AND slot = ?";
        List<Object> params = new ArrayList<>();
        params.add(slotPokemonEntity.getTypeEntity().getId());
        params.add(pok_id);
        params.add(slotPokemonEntity.getSlot());
        DBUtil.update(connection, SQL, params);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM pokemon_types WHERE pok_id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

    public int insert(Connection connection, SlotPokemonEntity slotPokemonEntity, int pok_id) {
        final String SQL = "INSERT INTO pokemon_types (pok_id, type_id, slot) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(pok_id);
        params.add(slotPokemonEntity.getTypeEntity().getId());
        params.add(slotPokemonEntity.getSlot());
        DBUtil.insertNoId(connection, SQL, params);
        return pok_id;
    }

}
