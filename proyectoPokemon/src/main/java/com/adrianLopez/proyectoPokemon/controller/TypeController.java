package com.adrianLopez.proyectoPokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adrianLopez.proyectoPokemon.controller.model.type.TypeListWeb;
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
            List<TypeListWeb> typesWeb = typeDTOs.stream()
                .map(TypeMapper.mapper::toTypeListWeb)
                .toList();
            Response response = Response.builder()
                .data(typesWeb)
                .build();

            return response;
    }
}