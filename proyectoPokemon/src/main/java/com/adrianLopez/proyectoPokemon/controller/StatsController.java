package com.adrianLopez.proyectoPokemon.controller;

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

import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsCreateWeb;
import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsDetailWeb;
import com.adrianLopez.proyectoPokemon.controller.model.stats.StatsUpdateWeb;
import com.adrianLopez.proyectoPokemon.domain.service.StatsService;
import com.adrianLopez.proyectoPokemon.http_response.Response;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;

@RequestMapping(StatsController.STATS)
@RestController
public class StatsController {

    @Value("${application.url}")
    private String urlBase;

    public static final String STATS = "/stats";

    @Autowired
    private StatsService statsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Response create(@PathVariable("id") int id, @RequestBody StatsCreateWeb statsCreateWeb) {
        statsService.create(StatsMapper.mapper.toStatsDTO(statsCreateWeb), id);
        StatsDetailWeb statsDetailWeb = new StatsDetailWeb(
                id,
                statsCreateWeb.getHp(),
                statsCreateWeb.getAtk(),
                statsCreateWeb.getDef(),
                statsCreateWeb.getSp_atk(),
                statsCreateWeb.getSp_def(),
                statsCreateWeb.getSpeed());
        return Response.builder().data(statsDetailWeb).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody StatsUpdateWeb statsUpdateWeb) {
        statsService.update(StatsMapper.mapper.toStatsDTO(statsUpdateWeb), id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        statsService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        StatsDetailWeb statsDetailWeb = StatsMapper.mapper.toStatsDetailWeb(statsService.find(id));
        statsDetailWeb.setPok_id(id);
        return Response.builder().data(statsDetailWeb).build();
    }

}