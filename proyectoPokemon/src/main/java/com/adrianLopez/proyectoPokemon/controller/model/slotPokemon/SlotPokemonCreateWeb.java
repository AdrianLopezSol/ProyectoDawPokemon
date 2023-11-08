package com.adrianLopez.proyectoPokemon.controller.model.slotPokemon;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeCreatePokemonWeb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotPokemonCreateWeb {

    private int slot;
    private TypeCreatePokemonWeb type;

}
