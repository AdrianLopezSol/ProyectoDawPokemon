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
    private Integer height;
    private Integer weight;
    private Integer exp;

    private List<SlotPokemonDTO> slotPokemonDTOs;
    private StatsDTO statsDTO;
}
