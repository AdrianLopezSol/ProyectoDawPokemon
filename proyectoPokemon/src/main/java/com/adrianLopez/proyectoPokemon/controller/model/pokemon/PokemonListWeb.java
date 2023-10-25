package com.adrianLopez.proyectoPokemon.controller.model.pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonListWeb {

    private int id;
    private String name;

    private int type_id1;
    private int type_id2;
    
}
