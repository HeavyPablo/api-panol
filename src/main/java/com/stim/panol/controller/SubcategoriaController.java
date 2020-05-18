package com.stim.panol.controller;

import com.stim.panol.model.Subcategoria;
import com.stim.panol.service.CategoriaServiceImpl;
import com.stim.panol.service.SubcategoriaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subcategoria")
@Slf4j
public class SubcategoriaController {

    @Autowired
    private SubcategoriaServiceImpl subcategoriaService;

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @GetMapping
    public ResponseEntity<List<Subcategoria>> getSubcategoria() {
        return ResponseEntity.ok(subcategoriaService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subcategoria> postCrearSubcategoria(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Subcategoria subcategoria = new Subcategoria(
                body.get("nombre"),
                dateFormat.format(date),
                dateFormat.format(date),
                categoriaService.findById(Integer.parseInt(body.get("categoria"))).get()
        );

        return ResponseEntity.ok(subcategoriaService.save(subcategoria));
    }

    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subcategoria>> postCrearSubcategorias(@Valid @NotNull @RequestBody ArrayList<Map<String, String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ArrayList<Subcategoria> subcategorias = new ArrayList<>();

        for (Map<String, String> object : body) {
            subcategorias.add( new Subcategoria(
                    object.get("nombre"),
                    dateFormat.format(date),
                    dateFormat.format(date),
                    categoriaService.findById(Integer.parseInt(object.get("categoria"))).get()
            ));
        }

        return ResponseEntity.ok(subcategoriaService.saveAll(subcategorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategoria> getByIdSubcategoria(@PathVariable Integer id) {
        if (!subcategoriaService.findById(id).isPresent()) {
            return null;
        }
        return ResponseEntity.ok(subcategoriaService.findById(id).get());
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subcategoria> postActualizaSubcategoria(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {

        if (!subcategoriaService.findById(id).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Subcategoria subcategoria = subcategoriaService.findById(id).get();

        subcategoria.setNombre(body.get("nombre"));
        subcategoria.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(subcategoriaService.save(subcategoria));
    }
}
