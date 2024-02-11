package com.adrianLopez.proyectoPokemon.common.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlotPokemonDTO {

    @Nullable
    Integer pok_id;

    @Nullable
    Integer slot;

    @Nullable
    TypeDTO typeDTO;
}
