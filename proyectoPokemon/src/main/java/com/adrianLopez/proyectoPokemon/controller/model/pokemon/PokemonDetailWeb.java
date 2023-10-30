package com.adrianLopez.proyectoPokemon.controller.model.pokemon;

import java.util.List;

import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;

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

    private List<TypeListWeb> types;
    private StatsDetailWeb stats;
    
}
