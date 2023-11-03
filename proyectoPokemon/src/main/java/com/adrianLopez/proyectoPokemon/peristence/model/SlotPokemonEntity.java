package com.adrianLopez.proyectoPokemon.peristence.model;

import java.sql.Connection;

import com.adrianLopez.proyectoPokemon.peristence.dao.TypeDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotPokemonEntity {

    private int slot;
    private TypeEntity typeEntity;

    public SlotPokemonEntity(int slot) {
        this.slot = slot;
    }

    public TypeEntity getTypeEntity(Connection connection, TypeDAO typeDAO, int pok_id) {
        if(this.typeEntity == null) {
            this.typeEntity = typeDAO.findByPokemonIdAndSlot(connection, pok_id, this.slot).orElse(null);
        }
        return typeEntity;
    }

}