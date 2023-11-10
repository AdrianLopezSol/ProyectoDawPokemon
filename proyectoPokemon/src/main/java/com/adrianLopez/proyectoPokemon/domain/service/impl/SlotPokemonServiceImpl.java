package com.adrianLopez.proyectoPokemon.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.SlotPokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.service.SlotPokemonService;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;

public class SlotPokemonServiceImpl implements SlotPokemonService{

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    SlotPokemonRepository slotPokemonRepository;

    @Override
    public int create(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemon slotPokemon = SlotPokemonMapper.mapper.toSlotPokemon(slotPokemonDTO);
        if (!pokemonRepository.exists(id)){
            throw new ResourceNotFoundException("El pokemon con id: " + id + " no existe");
        }

        return slotPokemonRepository.insert(SlotPokemonMapper.mapper.toSlotPokemonDTO(slotPokemon), id);
    }

    @Override
    public void update(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemon slotPokemon = SlotPokemonMapper.mapper.toSlotPokemon(slotPokemonDTO);
        if (!slotPokemonRepository.exists(id)){
            throw new ResourceNotFoundException("Los tipos del pokemon con id: " + id + " no existen");
        }
        slotPokemonRepository.update(SlotPokemonMapper.mapper.toSlotPokemonDTO(slotPokemon), id);
    }

    @Override
    public void delete(int id) {
        if (!slotPokemonRepository.exists(id)){
            throw new ResourceNotFoundException("Los tipos del pokemon con id: " + id + " no existen");
        }
        slotPokemonRepository.delete(id);
    }
}
