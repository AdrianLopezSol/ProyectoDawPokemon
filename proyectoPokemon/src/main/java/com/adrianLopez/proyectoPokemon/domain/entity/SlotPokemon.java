package com.adrianLopez.proyectoPokemon.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotPokemon {

    int id;
    int slot;
    Type type;

}
