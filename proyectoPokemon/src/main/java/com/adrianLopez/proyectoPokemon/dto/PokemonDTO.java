package com.adrianLopez.proyectoPokemon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonDTO {

    private int id;
    private String name;
    private double height;
    private double weight;
    private int exp;
}
