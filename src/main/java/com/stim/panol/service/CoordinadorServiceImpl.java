package com.stim.panol.service;

import com.stim.panol.model.Coordinador;
import com.stim.panol.repository.CoordinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinadorServiceImpl implements CoordinadorService {

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @Override
    public List<Coordinador> findAll() {
        return coordinadorRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
    }

    @Override
    public <S extends Coordinador> List<S> saveAll(Iterable<S> entities) {
        return coordinadorRepository.saveAll(entities);
    }

    @Override
    public Optional<Coordinador> findById(Integer id) {
        return coordinadorRepository.findById(id);
    }

    @Override
    public Coordinador save(Coordinador coordinador) {
        return coordinadorRepository.save(coordinador);
    }

    @Override
    public Optional<Coordinador> findByRut(String rut) {
        return coordinadorRepository.findByRut(rut);
    }
}
