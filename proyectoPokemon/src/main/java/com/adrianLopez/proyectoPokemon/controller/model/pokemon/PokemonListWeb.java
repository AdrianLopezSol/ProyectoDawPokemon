package com.adrianLopez.proyectoPokemon.controller.model.pokemon;

import java.util.List;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;

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

    private List<TypeListWeb> types;

    
}
