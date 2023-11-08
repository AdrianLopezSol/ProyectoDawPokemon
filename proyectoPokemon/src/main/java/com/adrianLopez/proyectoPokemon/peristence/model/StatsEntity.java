package com.adrianLopez.proyectoPokemon.peristence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsEntity {

    private int hp;
    private int atk;
    private int def;
    private int sp_atk;
    private int sp_def;
    private int speed;

}