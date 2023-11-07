package com.adrianLopez.proyectoPokemon.controller.model.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsUpdateWeb {

    private int pok_id;

    private int hp;
    private int atk;
    private int def;
    private int sp_atk;
    private int sp_def;
    private int speed;
}
