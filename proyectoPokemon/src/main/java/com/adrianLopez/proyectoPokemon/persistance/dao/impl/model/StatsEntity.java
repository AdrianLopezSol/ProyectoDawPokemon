package com.adrianLopez.proyectoPokemon.persistance.dao.impl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "base_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stats_id;

    @Column(name = "b_hp", nullable = true)
    private Integer hp;
    @Column(name = "b_atk", nullable = true)
    private Integer atk;
    @Column(name = "b_def", nullable = true)
    private Integer def;
    @Column(name = "b_sp_atk", nullable = true)
    private Integer sp_atk;
    @Column(name = "b_sp_def", nullable = true)
    private Integer sp_def;
    @Column(name = "b_speed", nullable = true)
    private Integer speed;

}