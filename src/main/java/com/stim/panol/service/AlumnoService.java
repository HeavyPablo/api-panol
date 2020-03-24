package com.stim.panol.service;

import com.stim.panol.model.Alumno;

import java.util.List;
import java.util.Optional;

// Inteerfaz auxiliar para separar el codigo.
public interface AlumnoService {

    List<Alumno> findAll();
    <S extends Alumno> List<S> saveAll(Iterable<S> entities);
    Optional<Alumno> findById(Integer id);
    Alumno save(Alumno alumno);
    Optional<Alumno> findByRut(String rut);
}
