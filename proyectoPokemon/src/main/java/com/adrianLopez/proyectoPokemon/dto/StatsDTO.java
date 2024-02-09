package com.adrianLopez.proyectoPokemon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatsDTO {
    
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer sp_atk;
    private Integer sp_def;
    private Integer speed;
}
