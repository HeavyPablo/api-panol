package com.stim.panol.controller;

import com.stim.panol.model.Director;
import com.stim.panol.service.DirectorService;
import com.stim.panol.service.DirectorServiceImpl;
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
@RequestMapping("/director")
@Slf4j
public class DirectorController {

    @Autowired
    private DirectorServiceImpl directorService;

    @Autowired
    private EscuelaServiceImpl escuelaService;


    @GetMapping
    public ResponseEntity<List<Director>> getAllDirector() {
        return ResponseEntity.ok(directorService.findAll());
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public Director postCrearDirector(@Valid @RequestBody @NotNull Map<String, String> body) {
        if (directorService.findByRut(body.get("rut")).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Director director = new Director(
                body.get("rut"),
                body.get("apellidoPaterno"),
                body.get("apellidoMaterno"),
                body.get("nombre"),
                body.get("telefono"),
                body.get("correoDirector"),
                dateFormat.format(date),
                dateFormat.format(date),
                escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()
        );

        return directorService.save(director);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Director>> getDirector(@PathVariable Integer id) {
        if (!directorService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(directorService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Director> postActualizarDirector(@Valid @RequestBody @NotNull Map<String, String> body, @PathVariable Integer id) {
        if (!directorService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Director director = directorService.findById(id).get();

        director.setApellidoMaterno(body.get("apellidoMaterno"));
        director.setApellidoPaterno(body.get("apellidoPaterno"));
        director.setEscuela(escuelaService.findById(Integer.parseInt(body.get("escuela"))).get());
        director.setCorreoDirector(body.get("correoDirector"));
        director.setNombre(body.get("nombre"));
        director.setTelefono(body.get("telefono"));
        director.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(directorService.save(director));
    }
}
