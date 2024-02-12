package com.adrianLopez.proyectoPokemon.common.dto;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonDTO {

    @Nullable
    Integer id;

    @Nullable
    String name;

    @Nullable
    @Max(value = 201, message = "La altura debe ser inferior a 20m")
    @Min(value = 1, message = "La altura debe ser superior a 0")
    Integer height;

    @Max(value = 10000, message = "El peso no puede ser superior a 999kg")
    @Nullable
    @Min(value = 1, message = "El peso debe ser superior a 0")
    Integer weight;

    @Nullable
    @Min(value = 1, message = "La experiencia debe ser superior a 0")
    Integer exp;

    @Nullable
    private List<SlotPokemonDTO> slotPokemonDTOs;

    @Nullable
    private StatsDTO statsDTO;
}
