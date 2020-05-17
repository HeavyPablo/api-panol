package com.stim.panol.service;

import com.stim.panol.model.Categoria;
import com.stim.panol.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
    }

    @Override
    public <S extends Categoria> List<S> saveAll(Iterable<S> entities) {
        return categoriaRepository.saveAll(entities);
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
