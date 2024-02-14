package com.adrianLopez.proyectoPokemon.common.dto;

import java.util.List;

import com.adrianLopez.proyectoPokemon.common.exception.DtoValidationException;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonDTO {

    @Nullable
    Integer id;

    @Nullable
    String name;

    @Nullable
    @Max(value = 201, message = "La altura debe ser inferior a 20m")
    @Min(value = 1, message = "La altura debe ser superior a 0")
    Integer height;

    @Max(value = 10000, message = "El peso no puede ser superior a 999kg")
    @Nullable
    @Min(value = 1, message = "El peso debe ser superior a 0")
    Integer weight;

    @Nullable
    @Min(value = 1, message = "La experiencia debe ser superior a 0")
    Integer exp;

    @Nullable
    private List<SlotPokemonDTO> slotPokemonDTOs;

    @Nullable
    private StatsDTO statsDTO;

    public void setWeight(Integer weight){
        if(this.height != null && weight != null &&  this.height > 100*weight){
            throw new DtoValidationException("Un pokemon no puede nedir mas de 100 veces su peso.");
        }
        this.weight = weight;
    }

    public void setHeight(Integer height){
        if(this.weight != null && height != null && height > 100*this.weight){
            throw new DtoValidationException("Un pokemon no puede nedir mas de 100 veces su peso.");
        }
        this.height = height;
    }
}
