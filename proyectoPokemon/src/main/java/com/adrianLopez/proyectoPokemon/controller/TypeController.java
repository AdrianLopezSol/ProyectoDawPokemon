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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.type.TypeUpdateWeb;
import com.adrianLopez.proyectoPokemon.domain.service.TypeService;
import com.adrianLopez.proyectoPokemon.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.http_response.Response;
import com.adrianLopez.proyectoPokemon.mapper.TypeMapper;

@RequestMapping(TypeController.TYPES)
@RestController
public class TypeController {

    @Value("${application.url}")
    private String urlBase;

    public static final String TYPES = "/types";

    @Autowired
    private TypeService typeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll() {
        List<TypeDTO> typeDTOs = typeService.findAll();
        List<TypeDetailWeb> typesWeb = typeDTOs.stream()
            .map(TypeMapper.mapper::toTypeDetailWeb)
            .toList();
        Response response = Response.builder()
            .data(typesWeb)
            .build();

        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody TypeCreateWeb typeCreateWeb){
        int id = typeService.create(TypeMapper.mapper.toTypeDTO(typeCreateWeb));
        TypeDetailWeb typeDetailWeb = new TypeDetailWeb(
                id,
                typeCreateWeb.getName()
        );
        return Response.builder().data(typeDetailWeb).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody TypeUpdateWeb typeUpdateWeb) {
        typeUpdateWeb.setId(id);
        typeService.update(TypeMapper.mapper.toTypeDTO(typeUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        typeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(TypeMapper.mapper.toTypeDetailWeb(typeService.find(id))).build();
    }

}