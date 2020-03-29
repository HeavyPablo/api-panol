package com.stim.panol.service;

import com.stim.panol.model.Docente;
import com.stim.panol.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public <S extends Docente> List<S> saveAll(Iterable<S> entities) {
        return docenteRepository.saveAll(entities);
    }

    @Override
    public Optional<Docente> findById(Integer id) {
        return docenteRepository.findById(id);
    }

    @Override
    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    public Optional<Docente> findByRut(String rut) {
        return docenteRepository.findByRut(rut);
    }
}