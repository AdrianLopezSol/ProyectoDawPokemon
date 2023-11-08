package com.adrianLopez.proyectoPokemon.peristence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;
import com.adrianLopez.proyectoPokemon.peristence.model.StatsEntity;

@Component
public class StatsDAO {

    public Optional<StatsEntity> find(Connection connection, int id) {
        final String sql = "SELECT * FROM base_stats WHERE pok_id = ?";
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(id));
            return Optional.ofNullable(resultSet.next() ? StatsMapper.mapper.toStatsEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(Connection connection, StatsEntity statsEntity, int id) {
        final String SQL = """
                    UPDATE base_stats SET b_hp = ?, b_atk = ?, b_def = ?,
                        b_sp_atk = ?, b_sp_def = ?, b_speed = ? WHERE pok_id = ?
                """;
        List<Object> params = new ArrayList<>();
        params.add(statsEntity.getHp());
        params.add(statsEntity.getAtk());
        params.add(statsEntity.getDef());
        params.add(statsEntity.getSp_atk());
        params.add(statsEntity.getSp_def());
        params.add(statsEntity.getSpeed());
        params.add(id);
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM base_stats WHERE pok_id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

    public int insert(Connection connection, StatsEntity statsEntity, int id) {
        final String SQL = "INSERT INTO base_stats (pok_id, b_hp, b_atk, b_def, b_sp_atk, b_sp_def, b_speed) VALUES (?, ?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(id);
        params.add(statsEntity.getHp());
        params.add(statsEntity.getAtk());
        params.add(statsEntity.getDef());
        params.add(statsEntity.getSp_atk());
        params.add(statsEntity.getSp_def());
        params.add(statsEntity.getSpeed());
        DBUtil.insertNoId(connection, SQL, params);
        return id;
    }

}
