package com.adrianLopez.proyectoPokemon.peristence.dao.impl.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pok_id")
    private int id;
    @Column(name = "pok_name")
    private String name;
    @Column(name = "pok_height", nullable = true)
    private Integer height;
    @Column(name = "pok_weight", nullable = true)
    private Integer weight;
    @Column(name = "pok_base_experience", nullable = true)
    private Integer exp;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pok_id")
    private List<SlotPokemonEntity> slotPokemonEntities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pok_id")
    private StatsEntity statsEntity;

}
