package com.adrianLopez.proyectoPokemon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrianLopez.proyectoPokemon.domain.entity.Pokemon;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;

@RequestMapping("/pokemon")
@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;
 
    @GetMapping("")
    public List<Pokemon> getAll() {
            return pokemonService.getAll();
    }
 

    @GetMapping("/{id}")
    public Optional<Pokemon> find(@PathVariable("id") int id) {
            return pokemonService.find(id);
    }
    
}