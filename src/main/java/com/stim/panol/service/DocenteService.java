package com.stim.panol.service;

import com.stim.panol.model.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {

    List<Docente> findAll();
    <S extends Docente> List<S> saveAll(Iterable<S> entities);
    Optional<Docente> findById(Integer id);
    Docente save(Docente docente);
    Optional<Docente> findByRut(String rut);
}
