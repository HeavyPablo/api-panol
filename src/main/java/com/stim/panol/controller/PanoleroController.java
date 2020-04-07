package com.stim.panol.controller;

import com.stim.panol.model.Panolero;
import com.stim.panol.service.PanoleroServiceImpl;
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
@RequestMapping("/panolero")
@Slf4j
public class PanoleroController {

    @Autowired
    private PanoleroServiceImpl panoleroService;

    @GetMapping
    public ResponseEntity<List<Panolero>> getAllPanolero() {
        return ResponseEntity.ok(panoleroService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Panolero postCrearPanolero(@Valid @NotNull @RequestBody Map<String, String> body) {

        if (panoleroService.findByRut(body.get("rut")).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Panolero docente = new Panolero(
                body.get("rut"),
                body.get("apellidoPaterno"),
                body.get("apellidoMaterno"),
                body.get("nombre"),
                body.get("telefono"),
                body.get("correoPanolero"),
                "activo",
                dateFormat.format(date),
                dateFormat.format(date)
        );

        return panoleroService.save(docente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Panolero>> getPanolero(@PathVariable Integer id) {
        if (!panoleroService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(panoleroService.findById(id));
    }

    @GetMapping("{rut}")
    public ResponseEntity<Panolero> getByRutPanolero(@RequestParam String rut) {
        return ResponseEntity.ok(panoleroService.findByRut(rut).get());
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Panolero> postActualizarPanolero(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {
        if (!panoleroService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Panolero panolero = panoleroService.findById(id).get();

        panolero.setApellidoMaterno(body.get("apellidoMaterno"));
        panolero.setApellidoPaterno(body.get("apellidoPaterno"));
        panolero.setCorreoPanolero(body.get("correoPanolero"));
        panolero.setNombre(body.get("nombre"));
        panolero.setTelefono(body.get("telefono"));
        panolero.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(panoleroService.save(panolero));
    }
}
