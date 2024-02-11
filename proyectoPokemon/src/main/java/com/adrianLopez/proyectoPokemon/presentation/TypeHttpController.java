package com.adrianLopez.proyectoPokemon.presentation;

import java.util.stream.Stream;

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

import com.adrianLopez.proyectoPokemon.common.dto.TypeDTO;
import com.adrianLopez.proyectoPokemon.domain.service.TypeService;
import com.adrianLopez.proyectoPokemon.presentation.http_response.Response;
import com.adrianLopez.proyectoPokemon.presentation.mapper.TypePresentationMapper;
import com.adrianLopez.proyectoPokemon.presentation.request.TypeRequest;
import com.adrianLopez.proyectoPokemon.presentation.response.TypeResponse;

@RequestMapping(TypeHttpController.TYPES)
@RestController
public class TypeHttpController {

    public static final String TYPES = "/api/types";

    @Autowired
    TypeService typeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response
                .builder()
                .data(
                        TypePresentationMapper
                                .mapper
                                .toTypeResponse(
                                        typeService
                                                .find(id)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll() {
        Stream<TypeDTO> typeDTOStream = typeService.findAll();
        Stream<TypeResponse> typeResponseStream =
            typeDTOStream.map(
                        typeDTO -> {
                            return TypePresentationMapper.mapper.toTypeResponse(typeDTO);
                        }
        );
        Response response = Response.builder()
                .data(typeResponseStream)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody TypeRequest typeRequest){
        TypeDTO typeDTO = TypePresentationMapper.mapper.toTypeDTO(typeRequest);
        //validate(typeDTO);
        return Response
                .builder()
                .data(
                    TypePresentationMapper
                                .mapper
                                .toTypeResponse(
                                        typeService.create(typeDTO)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody TypeRequest typeRequest) {
        TypeDTO typeDTO = TypePresentationMapper.mapper.toTypeDTO(typeRequest);
        typeDTO.setId(id);
        //validate(actorDto);
        return Response
                .builder()
                .data(
                    TypePresentationMapper
                                .mapper
                                .toTypeResponse(
                                        typeService
                                                .update(typeDTO)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        typeService.delete(id);
    }
}
