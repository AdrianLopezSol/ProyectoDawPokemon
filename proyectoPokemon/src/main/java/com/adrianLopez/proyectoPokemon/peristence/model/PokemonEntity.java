package com.adrianLopez.proyectoPokemon.peristence.model;

import java.sql.Connection;
import java.util.List;

import com.adrianLopez.proyectoPokemon.peristence.dao.SlotPokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.StatsDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {

    private int id;
    private String name;
    private double height;
    private double weight;
    private int exp;

    private List<SlotPokemonEntity> slotPokemonEntities;
    private StatsEntity statsEntity;

    public StatsEntity getStatsEntity(Connection connection, StatsDAO statsDAO) {
        if(this.statsEntity == null) {
            this.statsEntity = statsDAO.findByPokemonId(connection, id).orElse(null);
        }
        return this.statsEntity;
    }

    public List<SlotPokemonEntity> getSlotPokemonEntities(Connection connection, SlotPokemonDAO slotPokemonDAO) {
        if(this.slotPokemonEntities == null) {
            this.slotPokemonEntities = slotPokemonDAO.findByPokemonId(connection, id);
        }
        return this.slotPokemonEntities;
    }

}
