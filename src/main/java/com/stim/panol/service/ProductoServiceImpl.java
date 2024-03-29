package com.stim.panol.service;

import com.stim.panol.model.Escuela;
import com.stim.panol.model.Producto;
import com.stim.panol.repository.ProductoRepository;
import com.stim.panol.service.iservice.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends Producto> List<S> saveAll(Iterable<S> entities) {
        return productoRepository.saveAll(entities);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByEstado(String estado) {
        return productoRepository.findByEstado(estado);
    }

    @Override
    public Optional<List<Producto>> findByEscuelaAndEstado(Escuela escuela, String estado) {
        return productoRepository.findByEscuelaAndEstado(escuela, estado);
    }
}
