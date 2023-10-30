package com.adrianLopez.proyectoPokemon.peristence.model;

import java.util.List;

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

    private List<TypeEntity> types;
    private StatsEntity stats;

}
