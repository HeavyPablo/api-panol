package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.repository.UsuarioRepository;
import com.stim.panol.service.*;
import com.stim.panol.service.iservice.RegBloqueoService;
import com.stim.panol.service.iservice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.stim.panol.repository.RegBloqueoRepository;
@RestController
public class RegBloqueoController {
    @Autowired
    private RegBloqueoRepository regBloqueoRepository;

    @Autowired
    private RegBloqueoService regBloqueoService;

    @Autowired
    private RegBloqueoServiceImpl regBloqueoServiceImpl;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/regbloqueo")
    public ResponseEntity<RegBloqueo> postRegBloqueo(@Valid @RequestBody @NotNull Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RegBloqueo bloqueado = new RegBloqueo(
                Integer.parseInt(body.get("idUsuarioRB")),
                body.get("nomUsuarioRB"),
                body.get("perfilRB"),
                Integer.parseInt(body.get("idResponsableRB")),
                body.get("nomResponsableRB"),
                body.get("perfilResponsableRB"),
                body.get("escuelaRB"),
                body.get("fechaCreacionUsuarioRB"),
                dateFormat.format(date),
                "",
                body.get("carreraBloqueado"),
                body.get("razon")
        );


        return ResponseEntity.ok(regBloqueoRepository.save(bloqueado));
    }

}
