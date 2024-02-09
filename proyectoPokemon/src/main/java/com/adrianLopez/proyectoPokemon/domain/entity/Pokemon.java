package com.adrianLopez.proyectoPokemon.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pokemon {

    private int id;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer exp;

    private List<SlotPokemon> slotPokemons;
    private Stats stats;
    
}
