package com.stim.panol.controller;

import com.stim.panol.model.Docente;
import com.stim.panol.service.DocenteServiceImpl;
import com.stim.panol.service.CarreraServiceImpl;
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
@RequestMapping("/docente")
@Slf4j
public class DocenteController {
    @Autowired
    private DocenteServiceImpl docenteService;

    @Autowired
    private CarreraServiceImpl carreraService;


    @GetMapping
    public ResponseEntity<List<Docente>> getAllDocente() {
        return ResponseEntity.ok(docenteService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Docente postCrearDocente(@Valid @NotNull @RequestBody Map<String, String> body) {

        if (docenteService.findByRut(body.get("rut")).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Docente docente = new Docente(
                body.get("rut"),
                body.get("apellidoPaterno"),
                body.get("apellidoMaterno"),
                body.get("nombre"),
                body.get("telefono"),
                body.get("correoDocente"),
                "activo",
                dateFormat.format(date),
                dateFormat.format(date),
                carreraService.findById(Integer.parseInt(body.get("carrera"))).get()
        );

        return docenteService.save(docente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Docente>> getDocente(@PathVariable Integer id) {
        if (!docenteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(docenteService.findById(id));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Docente> postActualizarDocente(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {
        if (!docenteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Docente docente = docenteService.findById(id).get();

        docente.setApellidoMaterno(body.get("apellidoMaterno"));
        docente.setApellidoPaterno(body.get("apellidoPaterno"));
        docente.setCarrera(carreraService.findById(Integer.parseInt(body.get("carrera"))).get());
        docente.setCorreoDocente(body.get("correoDocente"));
        docente.setNombre(body.get("nombre"));
        docente.setTelefono(body.get("telefono"));
        docente.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(docenteService.save(docente));
    }
}
