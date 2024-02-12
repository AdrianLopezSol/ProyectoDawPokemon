package com.adrianLopez.proyectoPokemon.presentation.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class StatsRequest {

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
