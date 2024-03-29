package com.stim.panol.service.iservice;

import com.stim.panol.model.LogSolicitud;

import java.util.List;
import java.util.Optional;

public interface LogSolicitudService {
    List<LogSolicitud> findAll();
    List<LogSolicitud> findByUsuarioSolicitante(int solicitante);
    <S extends LogSolicitud> List<S> saveAll(Iterable<S> entities);
    Optional<LogSolicitud> findById(Integer id);
    LogSolicitud save(LogSolicitud logSolicitud);
    List<LogSolicitud> findByTipoSolicitud(String tipoSolicitud);
    List<LogSolicitud> findByIdEscuelaSolcitante(int IdEscuelaSolicitante);
    List<LogSolicitud> findByLog(String log);
}
