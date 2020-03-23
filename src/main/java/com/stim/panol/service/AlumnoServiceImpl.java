package com.stim.panol.service;

import com.stim.panol.model.Alumno;
import com.stim.panol.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public <S extends Alumno> List<S> saveAll(Iterable<S> entities) {
        return alumnoRepository.saveAll(entities);
    }

    @Override
    public Optional<Alumno> findByRut(String rut) {
        return alumnoRepository.findByRut(rut);
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }
}
