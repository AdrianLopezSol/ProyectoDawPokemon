package com.adrianLopez.proyectoPokemon.common.dto;

import java.util.List;

import jakarta.annotation.Nullable;
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
    Integer height;

    @Nullable
    Integer weight;

    @Nullable
    Integer exp;

    @Nullable
    private List<SlotPokemonDTO> slotPokemonDTOs;

    @Nullable
    private StatsDTO statsDTO;
}
