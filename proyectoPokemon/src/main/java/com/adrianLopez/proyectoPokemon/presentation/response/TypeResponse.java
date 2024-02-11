package com.adrianLopez.proyectoPokemon.presentation.response;

import com.adrianLopez.proyectoPokemon.common.ApplicationProperties;
import com.adrianLopez.proyectoPokemon.presentation.TypeHttpController;

import lombok.Data;

@Data
public class TypeResponse {

    String link;

    int id;

    private String name;

    public void setId(int id) {
        this.id = id;
        this.link = String.join("/", ApplicationProperties.getUrl() + TypeHttpController.TYPES, Integer.toString(this.id)) ;
    }
}
