package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.entity.SlotPokemon;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.SlotPokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.service.SlotPokemonService;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;

@Service
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
    public void update(List<SlotPokemonDTO> slotPokemonDTO, int id) {
        List<SlotPokemon> slotPokemons = slotPokemonDTO.stream()
                                        .map(SlotPokemonMapper.mapper::toSlotPokemon)
                                        .toList();
        slotPokemonRepository.update(slotPokemons.stream().map(SlotPokemonMapper.mapper::toSlotPokemonDTO).toList(), id);
    }

    @Override
    public void delete(int id) {
        if (!slotPokemonRepository.exists(id)){
            throw new ResourceNotFoundException("Los tipos del pokemon con id: " + id + " no existen");
        }
        slotPokemonRepository.delete(id);
    }
}
