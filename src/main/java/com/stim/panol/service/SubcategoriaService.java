package com.stim.panol.service;

import com.stim.panol.model.Subcategoria;

import java.util.List;
import java.util.Optional;

public interface SubcategoriaService {
    List<Subcategoria> findAll();
    <S extends Subcategoria> List<S> saveAll(Iterable<S> entities);
    Optional<Subcategoria> findById(Integer id);
    Subcategoria save(Subcategoria subcategoria);
}
