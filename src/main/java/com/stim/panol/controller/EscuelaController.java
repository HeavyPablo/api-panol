package com.stim.panol.controller;

import com.stim.panol.model.Escuela;
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
import java.util.*;

// En los controladores va la logica del negocio. controllerRest se comporta como api.
@RestController
@RequestMapping("/escuela")
@Slf4j
public class EscuelaController {

    @Autowired
    private EscuelaServiceImpl escuelaService;

    // Obtener todas las escuelas
    @GetMapping
    public ResponseEntity<List<Escuela>> getAllEscuela() {
        return ResponseEntity.ok(escuelaService.findAll());
    }

    // Crear escuela -> debe recibir {nombre}
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Escuela> postCrearEscuela(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Escuela escuela = new Escuela(
                body.get("nombre"),
                dateFormat.format(date),
                dateFormat.format(date)
        );

        return ResponseEntity.ok(escuelaService.save(escuela));
    }

    // Guarda muchas escuelas (recibe un arreglo json)
    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Escuela>> postCrearEscuelas(@Valid @NotNull @RequestBody ArrayList<Map<String, String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        ArrayList<Escuela> escuelas = new ArrayList<>();

        for (Map<String, String> object : body) {
            escuelas.add(new Escuela(
                    object.get("nombre"),
                    dateFormat.format(date),
                    dateFormat.format(date)
            ));
        }

        return ResponseEntity.ok(escuelaService.saveAll(escuelas));
    }

    // Obtener escuela por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Escuela>> getEscuela(@PathVariable Integer id) {
        if (!escuelaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(escuelaService.findById(id));
    }

    // Actualizar escuela -> puede recibir {nombre}
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Escuela> postActualizarEscuela(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {
        if (!escuelaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Escuela escuela = escuelaService.findById(id).get();
        escuela.setNombre(body.get("nombre"));
        escuela.setFechaActualizacion(dateFormat.format(date));
        return ResponseEntity.ok(escuelaService.save(escuela));
    }
}
