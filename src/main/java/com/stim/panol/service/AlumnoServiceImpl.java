package com.stim.panol.service;

import com.stim.panol.model.Alumno;
import com.stim.panol.repository.AlumnoRepository;
import com.stim.panol.service.iservice.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Los servicios son donde se hace la interaccion con la base de datos ..  (Se ejecuta la query).
// Estos servicios implementan los metodos de la interfaz auxiliar.
@Service
public class AlumnoServiceImpl implements AlumnoService {
    // Se llama al repositorio para ejecutar las query por medio de metodos.
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends Alumno> List<S> saveAll(Iterable<S> entities) {
        return alumnoRepository.saveAll(entities);
    }

    @Override
    public Optional<Alumno> findById(Integer id) {
        return alumnoRepository.findById(id);
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Optional<Alumno> findByRut(String rut) {
        return alumnoRepository.findByRut(rut);
    }
}
