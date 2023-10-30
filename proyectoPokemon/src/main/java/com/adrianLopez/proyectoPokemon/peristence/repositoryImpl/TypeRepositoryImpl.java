package com.adrianLopez.proyectoPokemon.peristence.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.mapper.TypeMapper;
import com.adrianLopez.proyectoPokemon.peristence.dao.TypeDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.TypeEntity;

@Repository
public class TypeRepositoryImpl implements TypeRepository {

    @Autowired
    TypeDAO typeDAO;

    @Override
    public List<TypeDTO> findAll() {
        try(Connection connection = DBUtil.open(true)) {
            List<TypeEntity> typeEntities = typeDAO.findAll(connection);
            List<TypeDTO> typeDTOs = typeEntities.stream()
                    .map(TypeMapper.mapper::toTypeDTO)
                    .toList();
            return typeDTOs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TypeDTO> findByPokemonId(int id) {
        try (Connection connection= DBUtil.open(true)){
            List<TypeEntity> typeEntities = typeDAO.findByPokemonId(connection, id);
            List<TypeDTO> typeDTOs = typeEntities.stream()
                    .map(TypeMapper.mapper::toTypeDTO)
                    .toList();
            return typeDTOs;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
