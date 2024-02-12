package com.adrianLopez.proyectoPokemon.persistance.dao.impl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemon_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotPokemonEntity {

    private int slot;

    @Id
    private int pok_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TypeEntity typeEntity;
}