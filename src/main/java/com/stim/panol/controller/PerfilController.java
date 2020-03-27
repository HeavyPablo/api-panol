package com.stim.panol.controller;

import com.stim.panol.model.Perfil;
import com.stim.panol.service.PerfilServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/perfil")
@Slf4j
public class PerfilController {

    @Autowired
    private PerfilServiceImpl perfilService;

    @GetMapping
    public ResponseEntity<List<Perfil>> getAllPerfil() {
        return ResponseEntity.ok(perfilService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Perfil> postCrearPerfil(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Perfil perfil = new Perfil(
                body.get("perfil")
        );

        return ResponseEntity.ok(perfilService.save(perfil));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Perfil>> getPerfil(@PathVariable Integer id) {
        if (!perfilService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(perfilService.findById(id));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Perfil> postActualizarPerfil(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {
        if (!perfilService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Perfil perfil = perfilService.findById(id).get();
        perfil.setPerfil(body.get("perfil"));
        return ResponseEntity.ok(perfilService.save(perfil));
    }
}
