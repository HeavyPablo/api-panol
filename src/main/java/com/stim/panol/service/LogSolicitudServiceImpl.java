package com.stim.panol.service;

import com.stim.panol.model.LogSolicitud;
import com.stim.panol.repository.LogSolicitudRepository;
import com.stim.panol.service.iservice.LogSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogSolicitudServiceImpl implements LogSolicitudService {

    @Autowired
    private LogSolicitudRepository logSolicitudRepository;

    @Override
    public List<LogSolicitud> findAll() {
        return logSolicitudRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacionSolicitud"));
    }

    @Override
    public List<LogSolicitud> findByUsuarioSolicitante(int solicitante) {
        return logSolicitudRepository.findByIdUsuarioOrderByFechaCambioSolicitudDesc(solicitante);
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

    @Override
    public List<LogSolicitud> findByTipoSolicitud(String tipoSolicitud) {
        return logSolicitudRepository.findByTipoSolicitudOrderByFechaCambioSolicitudDesc(tipoSolicitud);
    }

    @Override
    public List<LogSolicitud> findByIdEscuelaSolcitante(int idEscuelaSolicitante) {
        return logSolicitudRepository.findByIdEscuelaSolicitanteOrderByFechaCambioSolicitudDesc(idEscuelaSolicitante);
    }

    @Override
    public List<LogSolicitud> findByLog(String log) {
        return logSolicitudRepository.findByLogOrderByFechaCambioSolicitudDesc(log);
    }
}
