package com.adrianLopez.proyectoPokemon.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pokemon {

    private int id;
    private String name;
    private int height;
    private int weight;
    private int exp;

    private List<SlotPokemon> slotPokemons;
    private Stats stats;

    public Pokemon() {
        this.slotPokemons = new ArrayList<>();
    }

    public boolean addSlotPokemon(SlotPokemon slotPokemonInsert) {
        for (SlotPokemon slotPokemon : slotPokemons) {
            if (slotPokemon.getSlot() == slotPokemonInsert.getSlot()) {
                return false;
            }
        }
        List<SlotPokemon> updatedList = new ArrayList<>(slotPokemons);
        updatedList.add(slotPokemonInsert);
        slotPokemons = Collections.unmodifiableList(updatedList);
        return true;
    }

    public boolean updateSlotPokemon(SlotPokemon slotPokemonUpdate) {
        for (SlotPokemon slotPokemon : slotPokemons) {
            if (slotPokemon.getSlot() == slotPokemonUpdate.getSlot()) {
                List<SlotPokemon> updatedList = new ArrayList<>(slotPokemons);
                updatedList.remove(slotPokemon);
                updatedList.add(slotPokemonUpdate);
                slotPokemons = Collections.unmodifiableList(updatedList);
                return true;
            }
        }
        return false;
    }
}
