package com.adrianLopez.proyectoPokemon.peristence.repositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.db.DBUtil;
import com.adrianLopez.proyectoPokemon.domain.repository.PokemonRepository;
import com.adrianLopez.proyectoPokemon.dto.PokemonDTO;
import com.adrianLopez.proyectoPokemon.dto.SlotPokemonDTO;
import com.adrianLopez.proyectoPokemon.mapper.PokemonMapper;
import com.adrianLopez.proyectoPokemon.mapper.SlotPokemonMapper;
import com.adrianLopez.proyectoPokemon.mapper.StatsMapper;
import com.adrianLopez.proyectoPokemon.peristence.dao.PokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.SlotPokemonDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.StatsDAO;
import com.adrianLopez.proyectoPokemon.peristence.dao.TypeDAO;
import com.adrianLopez.proyectoPokemon.peristence.model.PokemonEntity;
import com.adrianLopez.proyectoPokemon.peristence.model.SlotPokemonEntity;

@Repository
public class PokemonRepositoryImpl implements PokemonRepository {

    @Autowired
    PokemonDAO pokemonDAO;

    @Autowired
    StatsDAO statsDAO;

    @Autowired
    TypeDAO typeDAO;

    @Autowired
    SlotPokemonDAO slotPokemonDAO;

    @Override
    public List<PokemonDTO> getAll(Integer page, Integer pageSize) {
        try (Connection connection = DBUtil.open(true)) {
            List<PokemonEntity> pokemonEntities = pokemonDAO.findAll(connection, page, pageSize);
            List<PokemonDTO> pokemonDTOs = pokemonEntities.stream()
                    .map(pokemonEntity -> {
                        pokemonEntity.getSlotPokemonEntities(connection, slotPokemonDAO).forEach(slotPokemonEntity -> {
                            slotPokemonEntity.getTypeEntity(connection, typeDAO, pokemonEntity.getId());
                        });
                        return PokemonMapper.mapper.toPokemonDTO(pokemonEntity);
                    })
                    .toList();
            return pokemonDTOs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PokemonDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<PokemonEntity> optionalPokemonEntity = pokemonDAO.find(connection, id);

            if (optionalPokemonEntity.isPresent()) {
                PokemonEntity pokemonEntity = optionalPokemonEntity.get();
                pokemonEntity.getStatsEntity(connection, statsDAO);
                List<SlotPokemonEntity> slotPokemonEntities = pokemonEntity.getSlotPokemonEntities(connection,
                        slotPokemonDAO);
                if (slotPokemonEntities != null) {
                    slotPokemonEntities
                            .forEach(slotPokemonEntity -> slotPokemonEntity.getTypeEntity(connection, typeDAO,
                                    pokemonEntity.getId()));
                } else {
                    pokemonEntity.setSlotPokemonEntities(new ArrayList<SlotPokemonEntity>());
                }
                return Optional.ofNullable(PokemonMapper.mapper.toPokemonDTO(pokemonEntity));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try (Connection connection = DBUtil.open(true)) {
            return pokemonDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(PokemonDTO pokemonDTO) {
        try (Connection connection = DBUtil.open(false)) {
            try {
                int pok_id = pokemonDAO.insert(connection, PokemonMapper.mapper.toPokemonEntity(pokemonDTO));
                for (SlotPokemonDTO slotPokemonDTO : pokemonDTO.getSlotPokemonDTOs()) {
                    slotPokemonDAO.insert(connection, SlotPokemonMapper.mapper.toSlotPokemonEntity(slotPokemonDTO),
                            pok_id);
                }
                statsDAO.insert(connection, StatsMapper.mapper.toStatsEntity(pokemonDTO.getStatsDTO()), pok_id);
                connection.commit();
                return pok_id;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PokemonDTO pokemonDTO) {
        try (Connection connection = DBUtil.open(false)) {
            try {
                pokemonDAO.update(connection, PokemonMapper.mapper.toPokemonEntity(pokemonDTO));
                for (SlotPokemonDTO slotPokemonDTO : pokemonDTO.getSlotPokemonDTOs()) {
                    slotPokemonDAO.update(connection, SlotPokemonMapper.mapper.toSlotPokemonEntity(slotPokemonDTO),
                            pokemonDTO.getId());
                }
                statsDAO.update(connection, StatsMapper.mapper.toStatsEntity(pokemonDTO.getStatsDTO()),
                        pokemonDTO.getId());
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists(int id) {
        try (Connection connection = DBUtil.open(true)) {
            return pokemonDAO.exists(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(false)) {
            try {
                statsDAO.delete(connection, id);
                slotPokemonDAO.delete(connection, id);
                pokemonDAO.delete(connection, id);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertPokemonType(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemonEntity slotPokemonEntity = SlotPokemonMapper.mapper.toSlotPokemonEntity(slotPokemonDTO);
        try (Connection connection = DBUtil.open(true)) {
            return slotPokemonDAO.insert(connection, slotPokemonEntity, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePokemonType(SlotPokemonDTO slotPokemonDTO, int id) {
        SlotPokemonEntity slotPokemonEntity = SlotPokemonMapper.mapper.toSlotPokemonEntity(slotPokemonDTO);
        try (Connection connection = DBUtil.open(true)) {
            slotPokemonDAO.update(connection, slotPokemonEntity, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePokemonType(int id) {
        try (Connection connection = DBUtil.open(true)) {
            slotPokemonDAO.delete(connection, id);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
