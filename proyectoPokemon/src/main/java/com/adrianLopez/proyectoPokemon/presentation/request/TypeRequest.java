package com.adrianLopez.proyectoPokemon.presentation.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class TypeRequest {

    @Nullable
    Integer id;

    @Nullable
    String name;
    
}
