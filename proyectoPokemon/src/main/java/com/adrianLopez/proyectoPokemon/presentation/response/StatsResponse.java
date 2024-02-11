package com.adrianLopez.proyectoPokemon.presentation.response;

import com.adrianLopez.proyectoPokemon.common.ApplicationProperties;
import com.adrianLopez.proyectoPokemon.presentation.StatsHttpController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class StatsResponse {

    String link;

    @JsonIgnore
    int pok_id;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer atk;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer def;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer sp_atk;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer sp_def;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer speed;

    public void setPok_id(int id) {
        this.pok_id = id;
        this.link = String.join("/", ApplicationProperties.getUrl() + StatsHttpController.STATS, Integer.toString(this.pok_id)) ;
    }
    
}
