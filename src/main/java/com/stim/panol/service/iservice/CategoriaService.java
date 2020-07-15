package com.stim.panol.service.iservice;

import com.stim.panol.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> findAll();
    <S extends Categoria> List<S> saveAll(Iterable<S> entities);
    Optional<Categoria> findById(Integer id);
    Categoria save(Categoria categoria);
}
