package com.stim.panol.controller;
import com.stim.panol.model.*;

import com.stim.panol.service.*;
import com.stim.panol.service.iservice.RegDebajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.stim.panol.repository.RegDebajaRepository;

@RestController
public class RegDebajaController {

    @Autowired
    private RegDebajaRepository regDebajaRepository;

    @Autowired
    private RegDebajaService regDebajaService;

    @Autowired
    private RegDebajaServiceImpl regDebajaServiceImpl;

    @PostMapping("/regdebaja")
    public ResponseEntity<RegDebaja> postRegDebaja(@Valid @RequestBody @NotNull Map<String, String> body) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RegDebaja debaja = new RegDebaja(
                Integer.parseInt(body.get("idUsuarioRD")),
                body.get("nomUsuarioRD"),
                body.get("perfilRD"),
                Integer.parseInt(body.get("idResponsableRD")),
                body.get("nomResponsableRD"),
                body.get("perfilResponsableRD"),
                body.get("escuelaRD"),
                body.get("fechaCreacionUsuarioRD"),
                dateFormat.format(date),
                body.get("carreraBloqueado"),
                body.get("razon")
        );

        return ResponseEntity.ok(regDebajaRepository.save(debaja));
    }

    }
