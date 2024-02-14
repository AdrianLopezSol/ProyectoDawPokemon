package com.adrianLopez.proyectoPokemon.presentation;

import java.util.stream.Stream;

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

import com.adrianLopez.proyectoPokemon.common.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.domain.service.PokemonService;
import com.adrianLopez.proyectoPokemon.presentation.http_response.Response;
import com.adrianLopez.proyectoPokemon.presentation.mapper.PokemonPresentationMapper;
import com.adrianLopez.proyectoPokemon.presentation.mapper.SlotPokemonPresentationMapper;
import com.adrianLopez.proyectoPokemon.presentation.request.PokemonRequest;
import com.adrianLopez.proyectoPokemon.presentation.request.SlotPokemonRequest;
import com.adrianLopez.proyectoPokemon.presentation.response.PokemonResponse;

import static com.adrianLopez.proyectoPokemon.common.dto.validation.Validation.validate;


@RequestMapping(PokemonHttpController.POKEMON)
@RestController
public class PokemonHttpController {

    @Value("${application.url}")
    private String urlBase;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public static final String POKEMON = "/api/pokemon";

    @Autowired
    PokemonService pokemonService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        Stream<PokemonDTO> pokemonDTOStream = (page != null)? pokemonService.getAll(page, pageSize) : pokemonService.getAll();

        Stream<PokemonResponse> PokemonResponseStream =
            pokemonDTOStream.map(
                        pokemonDTO -> {
                            return PokemonPresentationMapper.mapper.toPokemonResponse(pokemonDTO);
                        }
        );
        long totalRecords = pokemonService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(PokemonResponseStream)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        PokemonResponse pokemonResponse = PokemonPresentationMapper
                .mapper
                .toPokemonResponse(
                        pokemonService.find(id)
                );
        return Response
                .builder()
                .data(pokemonResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody PokemonRequest pokemonRequest) {
        PokemonDTO pokemonDTO = PokemonPresentationMapper
                .mapper
                .toPokemonDTO(pokemonRequest);
        validate(pokemonDTO);
        pokemonDTO.getSlotPokemonDTOs().forEach(slotPokemonDTO -> validate(slotPokemonDTO));
        validate(pokemonDTO.getStatsDTO());
        PokemonResponse PokemonResponse = PokemonPresentationMapper
                .mapper
                .toPokemonResponse(
                        pokemonService
                                .create(pokemonDTO)
                );
        return Response
                .builder()
                .data(PokemonResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody PokemonRequest pokemonRequest) {
        PokemonDTO pokemonDTO = PokemonPresentationMapper.mapper.toPokemonDTO(pokemonRequest);
        validate(pokemonDTO);
        pokemonDTO.getSlotPokemonDTOs().forEach(slotPokemonDTO -> validate(slotPokemonDTO));
        validate(pokemonDTO.getStatsDTO());
        pokemonDTO.setId(id);
        PokemonResponse PokemonResponse = PokemonPresentationMapper
                .mapper
                .toPokemonResponse(
                        pokemonService
                                .update(pokemonDTO)
                );
        return Response
                .builder()
                .data(PokemonResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        pokemonService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/types")
    public Response addTypePokemon(@PathVariable("id") int id, @RequestBody SlotPokemonRequest slotPokemonRequest){
        SlotPokemonDTO slotPokemonDTO = SlotPokemonPresentationMapper.mapper.toSlotPokemonDTO(slotPokemonRequest);
        validate(slotPokemonDTO);
        PokemonResponse pokemonResponse = PokemonPresentationMapper
                .mapper
                .toPokemonResponse(
                        pokemonService
                                .addPokemonType(slotPokemonDTO, id)
                );
        return Response
                .builder()
                .data(pokemonResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/types/{slotId}")
    public Response updateTypePokemon(
            @PathVariable("id") int id,
            @PathVariable("slotId") int slotId,
            @RequestBody SlotPokemonRequest slotPokemonRequest){
        SlotPokemonDTO slotPokemonDTO = SlotPokemonPresentationMapper.mapper.toSlotPokemonDTO(slotPokemonRequest);
        slotPokemonDTO.setSlot(slotId);
        validate(slotPokemonDTO);
        PokemonResponse PokemonResponse = PokemonPresentationMapper
                .mapper
                .toPokemonResponse(
                        pokemonService
                                .updatePokemonType(slotPokemonDTO, id)
                );
        return Response
                .builder()
                .data(PokemonResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}/types")
    public void deleteCharacterMovie(@PathVariable("id") int id) {
        pokemonService.deletePokemonTypes(id);
    }
}
