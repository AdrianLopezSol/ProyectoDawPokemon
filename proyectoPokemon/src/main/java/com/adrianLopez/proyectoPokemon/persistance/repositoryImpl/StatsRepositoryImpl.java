package com.adrianLopez.proyectoPokemon.persistance.repositoryImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Stats;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.persistance.dao.StatsDAO;
import com.adrianLopez.proyectoPokemon.persistance.mapper.StatsPersistanceMapper;

@Repository
public class StatsRepositoryImpl implements StatsRepository {

    @Qualifier("StatsDaoJpaImpl")
    final StatsDAO statsDAO;

    public StatsRepositoryImpl(StatsDAO statsDAO) {
        this.statsDAO = statsDAO;
    }

    @Override
    public Optional<Stats> find(int id) {
        return Optional.ofNullable(
                StatsPersistanceMapper
                        .mapper
                        .toStats(
                                statsDAO
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Stats save(Stats stats) {
        return StatsPersistanceMapper
                .mapper
                .toStats(
                        statsDAO
                                .save(
                                        StatsPersistanceMapper
                                                .mapper
                                                .toStatsDTO(stats)
                                )
                );
    }

    @Override
    public void delete(int id) {
        statsDAO.delete(id);
    }

}
