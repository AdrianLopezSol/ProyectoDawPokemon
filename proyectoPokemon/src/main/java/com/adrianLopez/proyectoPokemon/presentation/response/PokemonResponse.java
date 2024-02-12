package com.adrianLopez.proyectoPokemon.presentation.response;

import java.util.List;

import com.adrianLopez.proyectoPokemon.common.ApplicationProperties;
import com.adrianLopez.proyectoPokemon.presentation.PokemonHttpController;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonResponse {
    
    String link;
    
    int id;

    String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer height;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer weight;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer exp;

    @JsonProperty("stats")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    StatsResponse statsResponse;
    @JsonProperty("types")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<SlotPokemonResponse> slotPokemonResponses;

    public void setId(int id) {
        this.id = id;
        this.link = String.join("/",ApplicationProperties.getUrl() + PokemonHttpController.POKEMON, Integer.toString(this.id)) ;
    }
}
