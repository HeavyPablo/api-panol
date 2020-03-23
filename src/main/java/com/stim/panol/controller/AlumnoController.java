package com.stim.panol.controller;

import com.stim.panol.model.Alumno;
import com.stim.panol.service.AlumnoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
@Slf4j
public class AlumnoController {

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumno() {
        return ResponseEntity.ok(alumnoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Alumno> postCrearAlumno(@Valid @NotNull  @RequestBody Alumno alumno) {
        if (alumnoService.findByRut(alumno.getRut()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(alumnoService.save(alumno));
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Optional<Alumno>> getAlumno(@PathVariable String rut) {
        return ResponseEntity.ok(alumnoService.findByRut(rut));
    }

    @PutMapping("/{rut}")
    public ResponseEntity<Alumno> postActualizarAlumno(@Valid @NotNull @RequestBody Alumno alumno, @PathVariable String rut) {
        if (!alumnoService.findByRut(alumno.getRut()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(alumnoService.save(alumno));
    }
}
