package com.adrianLopez.proyectoPokemon.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

    private int id;
    private String name;
    private double height;
    private double weight;
    private int exp;

    private List<SlotPokemon> slotPokemons;
    private Stats stats;

}

