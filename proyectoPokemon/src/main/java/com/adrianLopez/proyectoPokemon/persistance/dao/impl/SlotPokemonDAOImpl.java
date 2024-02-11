package com.adrianLopez.proyectoPokemon.persistance.dao.impl;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrianLopez.proyectoPokemon.common.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.persistance.dao.SlotPokemonDAO;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.jpaRepository.SlotPokemonJpaRepository;
import com.adrianLopez.proyectoPokemon.persistance.dao.impl.mapper.SlotPokemonEntityMapper;

@Component
public class SlotPokemonDAOImpl implements SlotPokemonDAO {
    
    @Autowired
    SlotPokemonJpaRepository slotPokemonJpaRepository;

    @Override
    public Stream<SlotPokemonDTO> findByPokemonId(int id) {
        return slotPokemonJpaRepository
                    .findById(id)
                    .stream()
                    .map(SlotPokemonEntityMapper.mapper::toSlotPokemonDTO);
    }

    @Override
    public SlotPokemonDTO save(SlotPokemonDTO slotPokemonDTO) {
        return SlotPokemonEntityMapper
                .mapper
                .toSlotPokemonDTO(
                    slotPokemonJpaRepository
                                .save(
                                    SlotPokemonEntityMapper
                                                .mapper
                                                .toSlotPokemonEntity(slotPokemonDTO)
                                )
                );
    }

    @Override
    public void delete(int id) {
        slotPokemonJpaRepository.deleteById(id);
    }

}
