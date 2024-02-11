package com.adrianLopez.proyectoPokemon.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainHttpController {

    @GetMapping("")
    public String index() {
        return "Bienvenido a la API de pokemon";
    }

}
