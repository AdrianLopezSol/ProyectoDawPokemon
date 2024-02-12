package com.adrianLopez.proyectoPokemon.domain.entity;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotPokemon {

    @Nullable
    Integer id;

    @Nullable
    Integer slot;

    @Nullable
    Type type;

}
