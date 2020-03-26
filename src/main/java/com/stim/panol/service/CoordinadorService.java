package com.stim.panol.service;

import com.stim.panol.model.Coordinador;

import java.util.List;
import java.util.Optional;

public interface CoordinadorService {

    List<Coordinador> findAll();
    <S extends Coordinador> List<S> saveAll(Iterable<S> entities);
    Optional<Coordinador> findById(Integer id);
    Coordinador save(Coordinador coordinador);
    Optional<Coordinador> findByRut(String rut);
}
