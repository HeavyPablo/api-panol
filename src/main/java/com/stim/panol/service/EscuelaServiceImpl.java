package com.stim.panol.service;

import com.stim.panol.model.Escuela;
import com.stim.panol.repository.EscuelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscuelaServiceImpl implements EscuelaService {

    @Autowired
    private EscuelaRepository escuelaRepository;

    @Override
    public List<Escuela> findAll() {
        return escuelaRepository.findAll();
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
