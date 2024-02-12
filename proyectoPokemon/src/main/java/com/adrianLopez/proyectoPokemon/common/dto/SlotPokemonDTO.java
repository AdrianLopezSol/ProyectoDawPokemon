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

    @Min(value = 0, message = "La id debe ser 0 o superior")
    @Nullable
    Integer pok_id;

    @Min(value = 0, message = "El slot debe ser 0 o superior")
    @Nullable
    Integer slot;

    @Nullable
    TypeDTO typeDTO;
}
