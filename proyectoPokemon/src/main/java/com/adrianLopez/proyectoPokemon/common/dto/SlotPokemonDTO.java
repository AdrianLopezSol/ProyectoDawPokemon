package com.adrianLopez.proyectoPokemon.common.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlotPokemonDTO {

    @Nullable
    Integer id;

    @Min(value = 1, message = "El slot debe ser 1 o superior")
    @Nullable
    Integer slot;

    @Nullable
    TypeDTO typeDTO;
}
