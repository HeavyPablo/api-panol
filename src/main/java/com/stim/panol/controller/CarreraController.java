package com.stim.panol.controller;

import com.stim.panol.model.Carrera;
import com.stim.panol.service.CarreraServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrera")
@Slf4j
public class CarreraController {

    @Autowired
    private CarreraServiceImpl carreraService;

    @GetMapping
    public ResponseEntity<List<Carrera>> getAllCarrera() {
        return ResponseEntity.ok(carreraService.findAll());
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Carrera> postCarrera(@Valid @NotNull @RequestBody Carrera carrera) {
        if (carreraService.findById(carrera.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carreraService.save(carrera));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Carrera>> getCarrera(@PathVariable Integer id) {
        return ResponseEntity.ok(carreraService.findById(id));
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public ResponseEntity<Carrera> postActualizarCarrera(@Valid @NotNull @RequestBody Carrera carrera, @PathVariable Integer id) {
        if (!carreraService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carreraService.save(carrera));
    }
}
