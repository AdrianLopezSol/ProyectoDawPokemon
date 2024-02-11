package com.adrianLopez.proyectoPokemon.common.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeDTO {

    @Nullable
    Integer id;

    @Nullable
    String name;
}
