package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.service.*;
import com.stim.panol.service.iservice.RegActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.stim.panol.repository.RegActivoRepository;
@RestController
public class RegActivoController {
    @Autowired
    private RegActivoRepository regActivoRepository;

    @Autowired
    private RegActivoService regActivoService;

    @Autowired
    private RegActivoServiceImpl regActivoServiceImpl;

    @PostMapping("/regactivo")
    public ResponseEntity<RegActivo> postRegActivo(@Valid @RequestBody @NotNull Map<String, String> body) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        RegActivo activado = new RegActivo(
                Integer.parseInt(body.get("idUsuarioRA")),
                body.get("nomUsuarioRA"),
                body.get("perfilRA"),
                body.get("estadoRA"),
                Integer.parseInt(body.get("idResponsableRA")),
                body.get("nomResponsableRA"),
                body.get("perfilResponsableRA"),
                body.get("escuelaRA"),
                body.get("fechaCreacionUsuarioRA"),
                dateFormat.format(date),
                body.get("carreraActivada"),
                body.get("justificacion")
        );

        return ResponseEntity.ok(regActivoRepository.save(activado));
    }
}
