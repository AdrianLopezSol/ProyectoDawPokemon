package com.adrianLopez.proyectoPokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonListWeb;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.http_response.Response;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;

@RequestMapping(PokemonController.POKEMON)
@RestController
public class PokemonController {

        @Value("${application.url}")
        private String urlBase;

        public static final String POKEMON = "/pokemon";

        @Autowired
        private PokemonService pokemonService;

        @Value("${page.size}")
        private int PAGE_SIZE;

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("")
        public Response getAll(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer pageSize) {
                int totalRecords = pokemonService.getTotalNumberOfRecords();
                pageSize = (pageSize != null) ? pageSize : PAGE_SIZE;
                List<PokemonDTO> pokemonDTOs = (page != null) ? pokemonService.getAll(page, pageSize)
                                : pokemonService.getAll();
                List<PokemonListWeb> pokemonsWeb = pokemonDTOs.stream()
                                .map(PokemonMapper.mapper::toPokemonListWeb)
                                .toList();
                Response response = Response.builder()
                                .data(pokemonsWeb)
                                .totalRecords(totalRecords)
                                .build();

                if (page != null) {
                        response.paginate(page, pageSize, urlBase);
                }
                return response;
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/{id}")
        public Response find(@PathVariable("id") int id) {
                System.out.println(PokemonMapper.mapper.toPokemonDetailWeb(pokemonService.find(id)).getStatsDetailWeb().getAtk());
                return Response.builder()
                                .data(PokemonMapper.mapper.toPokemonDetailWeb(pokemonService.find(id)))
                                .build();
        }

}