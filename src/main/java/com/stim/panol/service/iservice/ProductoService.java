package com.stim.panol.service.iservice;

import com.stim.panol.model.Escuela;
import com.stim.panol.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    <S extends Producto> List<S> saveAll(Iterable<S> entities);
    Optional<Producto> findById(Integer id);
    Producto save(Producto producto);
    void deleteById(Integer id);
    List<Producto> findByEstado(String estado);
    Optional<List<Producto>> findByEscuelaAndEstado(Escuela escuela, String estado);
}
