package com.stim.panol.service;

import com.stim.panol.model.Solicitud;
import com.stim.panol.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService{

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    public List<Solicitud> findAll() {
        return solicitudRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
    }

    @Override
    public List<Solicitud> findByEstado(String estado) {
        return solicitudRepository.findByEstado(estado);
    }

    @Override
    public <S extends Solicitud> List<S> saveAll(Iterable<S> entities) {
        return solicitudRepository.saveAll(entities);
    }

    @Override
    public Optional<Solicitud> findById(Integer id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public Solicitud save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }
}
