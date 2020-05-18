package com.stim.panol.controller;

import com.stim.panol.model.Coordinador;
import com.stim.panol.service.CoordinadorServiceImpl;
import com.stim.panol.service.EscuelaServiceImpl;
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
@RequestMapping("/coordinador")
@Slf4j
public class CoordinadorController {

    @Autowired
    private CoordinadorServiceImpl coordinadorService;

    @Autowired
    private EscuelaServiceImpl escuelaService;


    @GetMapping
    public ResponseEntity<List<Coordinador>> getAllCoordinador() {
        return ResponseEntity.ok(coordinadorService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Coordinador postCrearCoordinador(@Valid @RequestBody @NotNull Map<String, String> body) {
        if (coordinadorService.findByRut(body.get("rut")).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Coordinador coordinador = new Coordinador(
                body.get("rut"),
                body.get("apellidoPaterno"),
                body.get("apellidoMaterno"),
                body.get("nombre"),
                body.get("telefono"),
                body.get("correoCoordinador"),
                dateFormat.format(date),
                dateFormat.format(date),
                escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()
        );

        return coordinadorService.save(coordinador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Coordinador>> getCoordinador(@PathVariable Integer id) {
        if (!coordinadorService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(coordinadorService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Coordinador> postActualizarCoordinador(@Valid @RequestBody @NotNull Map<String, String> body, @PathVariable Integer id) {
        if (!coordinadorService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Coordinador coordinador = coordinadorService.findById(id).get();

        coordinador.setApellidoMaterno(body.get("apellidoMaterno"));
        coordinador.setApellidoPaterno(body.get("apellidoPaterno"));
        coordinador.setEscuela(escuelaService.findById(Integer.parseInt(body.get("escuela"))).get());
        coordinador.setCorreoCoordinador(body.get("correoCoordinador"));
        coordinador.setNombre(body.get("nombre"));
        coordinador.setTelefono(body.get("telefono"));
        coordinador.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(coordinadorService.save(coordinador));
    }
}
