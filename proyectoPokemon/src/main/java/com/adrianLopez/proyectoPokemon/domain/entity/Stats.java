package com.adrianLopez.proyectoPokemon.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    Integer stats_id;
    
    Integer hp;

    Integer atk;

    Integer def;

    Integer sp_atk;

    Integer sp_def;

    Integer speed;
}
