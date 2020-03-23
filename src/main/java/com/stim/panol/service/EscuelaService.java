package com.stim.panol.service;

import com.stim.panol.model.Escuela;

import java.util.List;
import java.util.Optional;

public interface EscuelaService {

    List<Escuela> findAll();
    <S extends Escuela> List<S> saveAll(Iterable<S> entities);
    Optional<Escuela> findById(Integer id);
    Escuela save(Escuela escuela);

}
