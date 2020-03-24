package com.stim.panol.controller;

import com.stim.panol.model.Carrera;
import com.stim.panol.service.CarreraServiceImpl;
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
@RequestMapping("/carrera")
@Slf4j
public class CarreraController {

    @Autowired
    private CarreraServiceImpl carreraService;
    @Autowired
    private EscuelaServiceImpl escuelaService;

    // Obtener todas las carreras
    @GetMapping
    public ResponseEntity<List<Carrera>> getAllCarrera() {
        return ResponseEntity.ok(carreraService.findAll());
    }

    // Crear carrera -> debe recibir {nombre, tipo}
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carrera> postCarrera(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Carrera newCarrera = new Carrera(
                body.get("nombre"),
                body.get("tipo"),
                dateFormat.format(date),
                dateFormat.format(date),
                escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()
        );

        return ResponseEntity.ok(carreraService.save(newCarrera));
    }

    // Obtener carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Carrera>> getCarrera(@PathVariable Integer id) {

        if (!carreraService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carreraService.findById(id));
    }

    // Actualizar carrera
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carrera> postActualizarCarrera(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (!carreraService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Carrera carrera = carreraService.findById(id).get();
        carrera.setEscuela(escuelaService.findById(Integer.parseInt(body.get("escuela"))).get());
        carrera.setFechaActualizacion(dateFormat.format(date));
        carrera.setNombre(body.get("nombre"));

        return ResponseEntity.ok(carreraService.save(carrera));
    }
}
