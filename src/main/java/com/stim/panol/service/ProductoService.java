package com.stim.panol.service;

import com.stim.panol.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    <S extends Producto> List<S> saveAll(Iterable<S> entities);
    Optional<Producto> findById(Integer id);
    Producto save(Producto producto);
}
