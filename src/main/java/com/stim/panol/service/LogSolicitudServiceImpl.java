package com.stim.panol.service;

import com.stim.panol.model.LogSolicitud;
import com.stim.panol.repository.LogSolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogSolicitudServiceImpl implements LogSolicitudService{

    @Autowired
    private LogSolicitudRepository logSolicitudRepository;

    @Override
    public List<LogSolicitud> findAll() {
        return logSolicitudRepository.findAll();
    }

    @Override
    public <S extends LogSolicitud> List<S> saveAll(Iterable<S> entities) {
        return logSolicitudRepository.saveAll(entities);
    }

    @Override
    public Optional<LogSolicitud> findById(Integer id) {
        return logSolicitudRepository.findById(id);
    }

    @Override
    public LogSolicitud save(LogSolicitud logSolicitud) {
        return logSolicitudRepository.save(logSolicitud);
    }
}