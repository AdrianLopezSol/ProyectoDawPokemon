package com.adrianLopez.proyectoPokemon.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class SlotPokemonRequest {

    @Nullable
    Integer pok_id;

    @Nullable
    Integer slot;

    @Nullable
    @JsonProperty("actor")
    TypeRequest typeRequest;
    
}
