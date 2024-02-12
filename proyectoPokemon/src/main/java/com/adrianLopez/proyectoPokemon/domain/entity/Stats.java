package com.adrianLopez.proyectoPokemon.domain.entity;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    @Nullable
    Integer stats_id;
    
    @Nullable
    Integer hp;

    @Nullable
    Integer atk;

    @Nullable
    Integer def;

    @Nullable
    Integer sp_atk;

    @Nullable
    Integer sp_def;

    @Nullable
    Integer speed;
}
