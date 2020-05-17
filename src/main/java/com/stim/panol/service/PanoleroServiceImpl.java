package com.stim.panol.service;

import com.stim.panol.model.Panolero;
import com.stim.panol.repository.PanoleroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanoleroServiceImpl implements PanoleroService {

    @Autowired
    private PanoleroRepository panoleroRepository;

    @Override
    public List<Panolero> findAll() {
        return panoleroRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
    }

    @Override
    public <S extends Panolero> List<S> saveAll(Iterable<S> entities) {
        return panoleroRepository.saveAll(entities);
    }

    @Override
    public Optional<Panolero> findById(Integer id) {
        return panoleroRepository.findById(id);
    }

    @Override
    public Panolero save(Panolero panolero) {
        return panoleroRepository.save(panolero);
    }

    @Override
    public Optional<Panolero> findByRut(String rut) {
        return panoleroRepository.findByRut(rut);
    }
}
