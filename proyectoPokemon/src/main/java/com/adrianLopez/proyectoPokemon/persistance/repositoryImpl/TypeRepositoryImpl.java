package com.adrianLopez.proyectoPokemon.persistance.repositoryImpl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.adrianLopez.proyectoPokemon.domain.entity.Type;
import com.adrianLopez.proyectoPokemon.domain.repository.TypeRepository;
import com.adrianLopez.proyectoPokemon.persistance.dao.TypeDAO;
import com.adrianLopez.proyectoPokemon.persistance.mapper.TypePersistanceMapper;


@Repository
public class TypeRepositoryImpl implements TypeRepository {

    @Qualifier("TypeDaoImpl")
    final TypeDAO typeDAO;

    public TypeRepositoryImpl(TypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }

    @Override
    public Stream<Type> findAll() {
        return typeDAO
                .findAll()
                .map(typeDTO -> TypePersistanceMapper.mapper.toType(typeDTO));
    }

    @Override
    public Optional<Type> find(int id) {
        return Optional.ofNullable(
                TypePersistanceMapper
                        .mapper
                        .toType(
                                typeDAO
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Optional<Type> findByPokemonIdAndSlot(int id, int slot) {
        return Optional.ofNullable(
            TypePersistanceMapper
                        .mapper
                        .toType(
                            typeDAO
                                        .findByPokemonIdAndSlot(id, slot)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Type save(Type type) {
        return TypePersistanceMapper
                .mapper
                .toType(
                        typeDAO
                                .save(
                                        TypePersistanceMapper
                                                .mapper
                                                .toTypeDTO(type)
                                )
                );
    }

    @Override
    public void delete(int id) {
        typeDAO.delete(id);
    }
}
