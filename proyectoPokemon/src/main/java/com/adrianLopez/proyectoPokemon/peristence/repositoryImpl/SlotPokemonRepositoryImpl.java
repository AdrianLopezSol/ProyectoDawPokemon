package com.adrianLopez.proyectoPokemon.peristence.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.repository.SlotPokemonRepository;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;
import com.adrianLopez.proyectoPokemon.peristence.dao.SlotPokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.SlotPokemonEntity;

@Repository
public class SlotPokemonRepositoryImpl implements SlotPokemonRepository {

    @Autowired
    SlotPokemonDAO slotPokemonDAO;
    
    @Override
    public boolean exists(int id) {
        try (Connection connection = DBUtil.open(true)) {
            return slotPokemonDAO.exists(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemonEntity slotPokemonEntity = SlotPokemonMapper.mapper.toSlotPokemonEntity(slotPokemonDTO);
        try (Connection connection = DBUtil.open(true)) {
            return slotPokemonDAO.insert(connection, slotPokemonEntity, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(List<SlotPokemonDTO> slotPokemonDTOs, int id) {
        List<SlotPokemonEntity> slotPokemonEntities = slotPokemonDTOs
                                .stream()
                                .map(SlotPokemonMapper.mapper::toSlotPokemonEntity)
                                .toList();
        try (Connection connection = DBUtil.open(true)) {
            slotPokemonDAO.delete(connection, id);
            for (SlotPokemonEntity slotPokemonEntity : slotPokemonEntities) {
                slotPokemonDAO.insert(connection, slotPokemonEntity, id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)) {
            slotPokemonDAO.delete(connection, id);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
