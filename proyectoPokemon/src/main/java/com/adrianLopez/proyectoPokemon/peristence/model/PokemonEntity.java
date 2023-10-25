package com.adrianLopez.proyectoPokemon.peristence.model;

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
}
