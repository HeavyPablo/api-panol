package com.stim.panol.service;

import com.stim.panol.model.Subcategoria;
import com.stim.panol.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoriaServiceImpl implements SubcategoriaService{

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Override
    public List<Subcategoria> findAll() {
        return subcategoriaRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
    }

    @Override
    public <S extends Subcategoria> List<S> saveAll(Iterable<S> entities) {
        return subcategoriaRepository.saveAll(entities);
    }

    @Override
    public Optional<Subcategoria> findById(Integer id) {
        return subcategoriaRepository.findById(id);
    }

    @Override
    public Subcategoria save(Subcategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }
}
