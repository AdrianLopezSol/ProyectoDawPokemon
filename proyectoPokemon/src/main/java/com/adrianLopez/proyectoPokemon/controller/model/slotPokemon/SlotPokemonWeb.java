package com.adrianLopez.proyectoPokemon.controller.model.slotPokemon;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotPokemonWeb {

    private int slot;
    private TypeListWeb typeListWeb;

}
