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
    
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer sp_atk;
    private Integer sp_def;
    private Integer speed;
}
