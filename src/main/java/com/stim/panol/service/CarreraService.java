package com.stim.panol.service;

import com.stim.panol.model.Carrera;

import java.util.List;
import java.util.Optional;

// Inteerfaz auxiliar para separar el codigo.
public interface CarreraService {

    List<Carrera> findAll();
    <S extends Carrera> List<S> saveAll(Iterable<S> entities);
    Optional<Carrera> findById(Integer id);
    Carrera save(Carrera carrera);
}
