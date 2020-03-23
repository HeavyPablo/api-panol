package com.stim.panol.service;

import com.stim.panol.model.Carrera;
import com.stim.panol.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;


    @Override
    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    @Override
    public <S extends Carrera> List<S> saveAll(Iterable<S> entities) {
        return carreraRepository.saveAll(entities);
    }

    @Override
    public Optional<Carrera> findById(Integer id) {
        return carreraRepository.findById(id);
    }

    @Override
    public Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }
}
