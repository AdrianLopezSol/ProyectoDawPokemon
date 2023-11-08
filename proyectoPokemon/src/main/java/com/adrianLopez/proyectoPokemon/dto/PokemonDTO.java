package com.adrianLopez.proyectoPokemon.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonDTO {

    private int id;
    private String name;
    private int height;
    private int weight;
    private int exp;

    private List<SlotPokemonDTO> slotPokemonDTOs;
    private StatsDTO statsDTO;
}
