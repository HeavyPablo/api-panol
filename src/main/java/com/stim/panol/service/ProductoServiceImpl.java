package com.stim.panol.service;

import com.stim.panol.model.Producto;
import com.stim.panol.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
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
}
