package com.adrianLopez.proyectoPokemon.controller.model.pokemon;

import java.util.List;

import com.adrianLopez.proyectoPokemon.controller.model.slotPokemon.SlotPokemonCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsCreateWeb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonCreateWeb {

    private int id;
    private String name;
    private int height;
    private int weight;
    private int exp;

    private List<SlotPokemonCreateWeb> slots;
    private StatsCreateWeb stats;
    
}