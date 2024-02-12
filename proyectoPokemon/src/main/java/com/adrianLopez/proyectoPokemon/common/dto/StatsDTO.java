package com.adrianLopez.proyectoPokemon.common.dto;

import com.adrianLopez.proyectoPokemon.common.dto.validation.ValidStat;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatsDTO {

    @Nullable
    Integer stats_id;

    @ValidStat
    @Nullable
    Integer hp;

    @ValidStat
    @Nullable
    Integer atk;

    @ValidStat
    @Nullable
    Integer def;

    @ValidStat
    @Nullable
    Integer sp_atk;

    @ValidStat
    @Nullable
    Integer sp_def;

    @ValidStat
    @Nullable
    Integer speed;
}
