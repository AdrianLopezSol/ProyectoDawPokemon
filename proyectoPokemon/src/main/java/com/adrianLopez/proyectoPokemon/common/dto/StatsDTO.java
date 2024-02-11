package com.adrianLopez.proyectoPokemon.common.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatsDTO {

    @Nullable
    Integer pok_id;
    
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
