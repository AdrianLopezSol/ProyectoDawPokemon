package com.adrianLopez.proyectoPokemon.controller.model.pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDetailWeb {

    private int id;
    private String name;
    private double height;
    private double weight;
    private int exp;
    
}
