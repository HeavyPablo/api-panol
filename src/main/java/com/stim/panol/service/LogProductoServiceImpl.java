package com.stim.panol.service;

import com.stim.panol.model.LogProducto;
import com.stim.panol.repository.LogProductoRepository;
import com.stim.panol.service.iservice.LogProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogProductoServiceImpl implements LogProductoService {

    @Autowired
    private LogProductoRepository logProductoRepository;

    @Override
    public List<LogProducto> findAll() {
        return logProductoRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    @Override
    public <S extends LogProducto> List<S> saveAll(Iterable<S> entities) {
        return logProductoRepository.saveAll(entities);
    }

    @Override
    public Optional<LogProducto> findById(Integer id) {
        return logProductoRepository.findById(id);
    }

    @Override
    public LogProducto save(LogProducto logProducto) {
        return logProductoRepository.save(logProducto);
    }

    @Override
    public List<LogProducto> findByEstado(String estado) {
        return logProductoRepository.findByEstadoOrderByFechaDesc(estado);
    }

    @Override
    public List<LogProducto> findByOperacion(String operacion) {
        return logProductoRepository.findByOperacionOrderByFechaDesc(operacion);
    }
}
