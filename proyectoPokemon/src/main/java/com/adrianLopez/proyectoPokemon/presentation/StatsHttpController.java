package com.adrianLopez.proyectoPokemon.presentation;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.adrianLopez.proyectoPokemon.common.dto.StatsDTO;
import com.adrianLopez.proyectoPokemon.domain.service.StatsService;
import com.adrianLopez.proyectoPokemon.presentation.http_response.Response;
import com.adrianLopez.proyectoPokemon.presentation.mapper.StatsPresentationMapper;
import com.adrianLopez.proyectoPokemon.presentation.request.StatsRequest;

@RequestMapping(StatsHttpController.STATS)
@RestController
public class StatsHttpController {

    public static final String STATS = "/api/stats";

    @Autowired
    StatsService statsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response
                .builder()
                .data(
                        StatsPresentationMapper.mapper
                                .toStatsResponse(
                                        statsService
                                                .find(id)))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody StatsRequest statsRequest) {
        StatsDTO statsDTO = StatsPresentationMapper.mapper.toStatsDTO(statsRequest);
        // validate(typeDTO);
        return Response
                .builder()
                .data(
                        StatsPresentationMapper.mapper
                                .toStatsResponse(
                                        statsService
                                                .create(statsDTO)))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody StatsRequest statsRequest) {
        StatsDTO statsDTO = StatsPresentationMapper.mapper.toStatsDTO(statsRequest);
        statsDTO.setPok_id(id);
        // validate(actorDto);
        return Response
                .builder()
                .data(
                        StatsPresentationMapper.mapper
                                .toStatsResponse(
                                        statsService
                                                .update(statsDTO)))
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        statsService.delete(id);
    }
}
