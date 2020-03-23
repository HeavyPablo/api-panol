package com.stim.panol.controller;

import com.stim.panol.model.Escuela;
import com.stim.panol.service.EscuelaService;
import com.stim.panol.service.EscuelaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/escuela")
@Slf4j
public class EscuelaController {

    @Autowired
    private EscuelaServiceImpl escuelaService;

    @GetMapping
    public ResponseEntity<List<Escuela>> getAllEscuela() {
        return ResponseEntity.ok(escuelaService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Escuela> postCrearEscuela(@Valid @NotNull @RequestBody Escuela escuela) {
        if (escuelaService.findById(escuela.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(escuelaService.save(escuela));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Escuela>> getEscuela(@PathVariable Integer id) {
        if (!escuelaService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(escuelaService.findById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Escuela> postActualizarEscuela(@Valid @NotNull @RequestBody Escuela escuela, @RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        if (!escuelaService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(escuelaService.save(escuela));
    }
}
