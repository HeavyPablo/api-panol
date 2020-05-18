package com.stim.panol.service;

import com.stim.panol.model.LogProducto;
import com.stim.panol.repository.LogProductoRepository;
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
        return logProductoRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
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
}
