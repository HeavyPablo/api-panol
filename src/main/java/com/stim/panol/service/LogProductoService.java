package com.stim.panol.service;

import com.stim.panol.model.LogProducto;

import java.util.List;
import java.util.Optional;

public interface LogProductoService {
    List<LogProducto> findAll();
    <S extends LogProducto> List<S> saveAll(Iterable<S> entities);
    Optional<LogProducto> findById(Integer id);
    LogProducto save(LogProducto logProducto);
    List<LogProducto> findByEstado(String estado);
    List<LogProducto> findByOperacion(String operacion);
}
