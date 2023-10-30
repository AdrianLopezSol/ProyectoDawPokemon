package com.adrianLopez.proyectoPokemon.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.StatsRepository;
import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private TypeRepository typeRepository;
 
    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        List<PokemonDTO> pokemonDTOs = new ArrayList<>();
        List<PokemonDTO> responseDTOs = new ArrayList<>();
        pokemonDTOs = pokemonRepository.getAll(page, pageSize);
        for (int i=0; i<pokemonDTOs.size(); i++){
        List<TypeDTO> typeDTOs = typeRepository.findByPokemonId(pokemonDTOs.get(i).getId());
        pokemonDTOs.get(i).setTypes(typeDTOs);
        responseDTOs.add(pokemonDTOs.get(i));
        }
        return responseDTOs;
        
    }

    @Override
    public List<PokemonDTO> getAll() {
        return pokemonRepository.getAll(null, null);
    }


    @Override
    public PokemonDTO find(int id) {

        PokemonDTO pokemonDTO = pokemonRepository.find(id);

        StatsDTO statsDTO = statsRepository.findByPokemonId(id);
        pokemonDTO.setStats(statsDTO);

        List<TypeDTO> typeDTOs = typeRepository.findByPokemonId(id);

        pokemonDTO.setTypes(typeDTOs);

        return pokemonDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return pokemonRepository.getTotalNumberOfRecords();
    }
}