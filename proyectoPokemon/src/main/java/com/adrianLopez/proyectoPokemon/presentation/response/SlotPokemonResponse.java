package com.adrianLopez.proyectoPokemon.presentation.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SlotPokemonResponse {

    @JsonIgnore
    private int pok_id;

    @JsonIgnore
    private int slot;

    @JsonProperty("type")
    private TypeResponse typeResponse;
    
}
