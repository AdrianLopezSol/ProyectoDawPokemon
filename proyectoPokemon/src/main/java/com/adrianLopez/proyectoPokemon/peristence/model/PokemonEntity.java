package com.adrianLopez.proyectoPokemon.peristence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {

    private int id;
    private String name;
    private double height;
    private double weight;
    private int exp;

    private int type_id1;
    private int type_id2;

    public void setTypes(Integer... types) {
        switch (types.length) {
            case 2:
                this.type_id2 = types[1];
            case 1:
                this.type_id1 = types[0];
        }
    }
}
