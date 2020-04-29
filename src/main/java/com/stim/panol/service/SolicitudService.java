package com.stim.panol.service;

import com.stim.panol.model.Solicitud;

import java.util.List;
import java.util.Optional;

public interface SolicitudService {
    List<Solicitud> findAll();
    List<Solicitud> findByEstado(String estado);
    <S extends Solicitud> List<S> saveAll(Iterable<S> entities);
    Optional<Solicitud> findById(Integer id);
    Solicitud save(Solicitud solicitud);
}
