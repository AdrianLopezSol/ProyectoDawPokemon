package com.adrianLopez.proyectoPokemon.domain.entity;

import java.util.List;
import java.util.Objects;

import com.adrianLopez.proyectoPokemon.common.exception.NotValidCombinationException;
import com.adrianLopez.proyectoPokemon.common.exception.ResourceNotFoundException;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pokemon {

    @Nullable
    Integer id;

    @Nullable
    String name;

    @Nullable
    Integer height;

    @Nullable
    Integer weight;

    @Nullable
    Integer exp;

    @Nullable
    private List<SlotPokemon> slotPokemons;

    @Nullable
    private Stats stats;

    public void addPokemonType(SlotPokemon slotPokemonAdd) {
        if(slotPokemonAdd.getType().getId() != null && slotPokemonAdd.getType().getId() != 9 && this.weight > 1000) {
            throw new NotValidCombinationException("El peso de un pokemon no puede ser "+ this.weight +" y no ser de tipo acero");
        }
        this.getSlotPokemons().stream()
                .filter(slotPokemon -> Objects.equals(slotPokemon.getType().getId(), slotPokemonAdd.getType().getId()))
                .findFirst()
                .ifPresentOrElse(characterMovie -> {
                    throw new NotValidCombinationException("Un Pokemon no puede tener 2 veces el mismo tipo");
                }, () -> {                     System.out.println("alerta");
                    slotPokemons.add(slotPokemonAdd);}
                );
    }

    public void updatePokemonType(SlotPokemon slotPokemonUpdate) {
        slotPokemons.stream()
                .filter(slotPokemon -> Objects.equals(slotPokemon.getSlot(), slotPokemonUpdate.getSlot()))
                .findFirst()
                .ifPresentOrElse(slotPokemon -> {
                    if (slotPokemon.getType().getId() == 9 && slotPokemonUpdate.getType().getId() != 9 && this.weight > 1000) {
                        throw new NotValidCombinationException("El peso de un pokemon no puede ser "+ this.weight +" y no ser de tipo acero");
                    }
                    System.out.println("alerta");
                    slotPokemon.setType(slotPokemonUpdate.getType());
                }, () -> {
                    throw new ResourceNotFoundException(
                            "Tipo no encontrado con el slot: " + slotPokemonUpdate.getSlot());
                });
    }

}
