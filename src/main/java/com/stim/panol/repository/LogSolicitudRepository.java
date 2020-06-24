package com.stim.panol.repository;

import com.stim.panol.model.LogSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSolicitudRepository extends JpaRepository<LogSolicitud, Integer> {
    List<LogSolicitud> findByIdUsuarioOrderByFechaCambioSolicitudDesc(Integer idUsuario);
    List<LogSolicitud> findByTipoSolicitudOrderByFechaCambioSolicitudDesc(String tipoSolicitud);
    List<LogSolicitud> findByIdEscuelaSolicitanteOrderByFechaCambioSolicitudDesc(Integer idEscuelaSolicitante);
}
