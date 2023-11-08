package com.adrianLopez.proyectoPokemon.peristence.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;
import com.adrianLopez.proyectoPokemon.peristence.dao.StatsDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.StatsEntity;

@Repository
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    StatsDAO statsDAO;

    @Override
    public Optional<StatsDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<StatsEntity> statsEntity = statsDAO.find(connection, id);
            if (statsEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(StatsMapper.mapper.toStatsDTO(statsEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int insert(StatsDTO statsDTO, int id) {
        try (Connection connection = DBUtil.open(true)) {
            return statsDAO.insert(connection, StatsMapper.mapper.toStatsEntity(statsDTO), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(StatsDTO statsDTO, int id) {
        try (Connection connection = DBUtil.open(true)) {
            statsDAO.update(connection, StatsMapper.mapper.toStatsEntity(statsDTO), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)) {
            statsDAO.delete(connection, id);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
