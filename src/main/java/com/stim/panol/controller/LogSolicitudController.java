package com.stim.panol.controller;

import com.stim.panol.model.LogSolicitud;
import com.stim.panol.service.LogSolicitudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/log-solicitud")
@Slf4j
public class LogSolicitudController {

    @Autowired
    private LogSolicitudServiceImpl logSolicitudService;

    @GetMapping
    public ResponseEntity<List<LogSolicitud>> getAllSolicitudes() {
        return ResponseEntity.ok(logSolicitudService.findAll());
    }

    @GetMapping("/{solicitante}")
    public ResponseEntity<List<LogSolicitud>> getSolicitudesBySolicitante(@PathVariable Integer solicitante) {
        return ResponseEntity.ok(logSolicitudService.findByUsuarioSolicitante(solicitante));
    }

    @GetMapping("/tipoSolicitud/{tipoSolicitud}")
    public ResponseEntity<List<LogSolicitud>> getSolicitudesByTipoSolicitud(@PathVariable String tipoSolicitud) {
        return ResponseEntity.ok(logSolicitudService.findByTipoSolicitud(tipoSolicitud));
    }

    @GetMapping("/escuelaSolicitante/{idEscuelaSolicitante}")
    public ResponseEntity<List<LogSolicitud>> getSolicitudesByIdEscuelaSolicitante(@PathVariable int idEscuelaSolicitante) {
        return ResponseEntity.ok(logSolicitudService.findByIdEscuelaSolcitante(idEscuelaSolicitante));
    }


}
