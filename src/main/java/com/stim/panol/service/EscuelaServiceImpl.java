package com.stim.panol.service;

import com.stim.panol.model.Escuela;
import com.stim.panol.repository.EscuelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Los servicios son donde se hace la interaccion con la base de datos.
// Estos servicios implementan los metodos de la interfaz auxiliar.
@Service
public class EscuelaServiceImpl implements EscuelaService {

    // Se llama al repositorio para ejecutar las query por medio de metodos.
    @Autowired
    private EscuelaRepository escuelaRepository;

    @Override
    public List<Escuela> findAll() {
        return escuelaRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
    }

    @Override
    public <S extends Escuela> List<S> saveAll(Iterable<S> entities) {
        return escuelaRepository.saveAll(entities);
    }

    @Override
    public Optional<Escuela> findById(Integer id) {
        return escuelaRepository.findById(id);
    }

    @Override
    public Escuela save(Escuela escuela) {
        return escuelaRepository.save(escuela);
    }
}
