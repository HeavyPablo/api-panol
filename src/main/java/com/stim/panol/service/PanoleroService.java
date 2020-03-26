package com.stim.panol.service;

import com.stim.panol.model.Panolero;

import java.util.List;
import java.util.Optional;

public interface PanoleroService {
    List<Panolero> findAll();
    <S extends Panolero> List<S> saveAll(Iterable<S> entities);
    Optional<Panolero> findById(Integer id);
    Panolero save(Panolero panolero);
    Optional<Panolero> findByRut(String rut);
}
