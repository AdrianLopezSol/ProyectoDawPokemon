package com.adrianLopez.proyectoPokemon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatsDTO {

    private int pok_id;

    private int hp;
    private int atk;
    private int def;
    private int sp_atk;
    private int sp_def;
    private int speed;
}
