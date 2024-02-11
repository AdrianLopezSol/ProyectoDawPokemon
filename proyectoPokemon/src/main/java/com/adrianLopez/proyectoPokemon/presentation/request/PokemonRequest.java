package com.adrianLopez.proyectoPokemon.presentation.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class PokemonRequest {

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
    @JsonProperty("stats")
    StatsRequest statsRequest;

    @Nullable
    @JsonProperty("types")
    List<SlotPokemonRequest> slotPokemonRequests;
}
