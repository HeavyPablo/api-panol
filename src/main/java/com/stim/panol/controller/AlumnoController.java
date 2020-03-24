package com.stim.panol.controller;

import com.stim.panol.model.Alumno;
import com.stim.panol.service.AlumnoServiceImpl;
import com.stim.panol.service.CarreraServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/alumno")
@Slf4j
public class AlumnoController {

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private CarreraServiceImpl carreraService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumno() {
        return ResponseEntity.ok(alumnoService.findAll());
    }

    // Debe recibir por post{rut, apellidoPaterno, apellidoMaterno, nombre, telefono, correoAlumno, carrera}
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> postCrearAlumno(@Valid @NotNull  @RequestBody Map<String, String> body) {

        if (alumnoService.findByRut(body.get("rut")).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Alumno alumno = new Alumno(
                body.get("rut"),
                body.get("apellidoPaterno"),
                body.get("apellidoMaterno"),
                body.get("nombre"),
                body.get("telefono"),
                body.get("correoAlumno"),
                "activo",
                dateFormat.format(date),
                dateFormat.format(date),
                carreraService.findById(Integer.parseInt(body.get("carrera"))).get()
        );

        return ResponseEntity.ok(alumnoService.save(alumno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Alumno>> getAlumno(@PathVariable Integer id) {
        if (!alumnoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnoService.findById(id));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> postActualizarAlumno(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {
        if (!alumnoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Alumno alumno = alumnoService.findById(id).get();

        alumno.setApellidoMaterno(body.get("apellidoMaterno"));
        alumno.setApellidoPaterno(body.get("apellidoPaterno"));
        alumno.setCarrera(carreraService.findById(Integer.parseInt(body.get("carrera"))).get());
        alumno.setCorreoAlumno(body.get("correoAlumno"));
        alumno.setNombre(body.get("nombre"));
        alumno.setTelefono(body.get("telefono"));
        alumno.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(alumnoService.save(alumno));
    }
}
