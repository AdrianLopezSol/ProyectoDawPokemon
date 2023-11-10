package com.adrianLopez.proyectoPokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.pokemon.PokemonListWeb;
import com.adrianLopez.proyectoPokemon.controller.model.slotPokemon.SlotPokemonCreateWeb;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.domain.service.SlotPokemonService;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.http_response.Response;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;

@RequestMapping(PokemonController.POKEMON)
@RestController
public class PokemonController {

        @Value("${application.url}")
        private String urlBase;

        public static final String POKEMON = "/pokemon";

        @Autowired
        private PokemonService pokemonService;

        @Autowired
        private SlotPokemonService slotPokemonService;

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
                return Response.builder()
                                .data(PokemonMapper.mapper.toPokemonDetailWeb(pokemonService.find(id)))
                                .build();
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping("")
        public Response create(@RequestBody PokemonCreateWeb pokemonCreateWeb) {
                int id = pokemonService.create(PokemonMapper.mapper.toPokemonDTO(pokemonCreateWeb));
                PokemonDetailWeb pokemonDetailWeb = PokemonMapper.mapper.toPokemonDetailWeb(pokemonService.find(id));
                pokemonDetailWeb.getStats().setPok_id(id);
                return Response.builder()
                                .data(pokemonDetailWeb)
                                .build();
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable("id") int id) {
                pokemonService.delete(id);
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @PutMapping("/{id}")
        public Response update(@PathVariable("id") int id, @RequestBody PokemonCreateWeb pokemonCreateWeb) {
                pokemonCreateWeb.setId(id);
                pokemonService.update(PokemonMapper.mapper.toPokemonDTO(pokemonCreateWeb));
                PokemonDetailWeb pokemonDetailWeb = PokemonMapper.mapper.toPokemonDetailWeb(pokemonService.find(id));
                pokemonDetailWeb.getStats().setPok_id(id);
                return Response.builder()
                                .data(pokemonDetailWeb)
                                .build();
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping( "types/{id}")
        public Response addType(@RequestBody SlotPokemonCreateWeb slotPokemonCreateWeb, @PathVariable("id") int id) {
                SlotPokemonDTO slotPokemonDTO = SlotPokemonMapper.mapper.toSlotPokemonDTO(slotPokemonCreateWeb);
                slotPokemonService.create(slotPokemonDTO, id);
                PokemonDetailWeb pokemonDetailWeb = PokemonMapper.mapper.toPokemonDetailWeb(pokemonService.find(id));
                pokemonDetailWeb.getStats().setPok_id(id);
                return Response.builder()
                                .data(pokemonDetailWeb)
                                .build();
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @PutMapping( "types/{id}")
        public void updateTypes(@RequestBody List<SlotPokemonCreateWeb> slotPokemonCreateWebs, @PathVariable("id") int id) {
                List<SlotPokemonDTO> slotPokemonDTos = slotPokemonCreateWebs.stream()
                    .map(SlotPokemonMapper.mapper::toSlotPokemonDTO)
                    .toList();
                slotPokemonService.update(slotPokemonDTos, id);
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping( "types/{id}")
        public void deleteTypes(@PathVariable("id") int id) {
                slotPokemonService.delete(id);
        }

}