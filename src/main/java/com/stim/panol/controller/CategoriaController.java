package com.stim.panol.controller;

import com.stim.panol.model.Categoria;
import com.stim.panol.service.CategoriaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
@Slf4j
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategoria() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> postCrearCategoria(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Categoria categoria = new Categoria(
                body.get("nombre"),
                dateFormat.format(date),
                dateFormat.format(date)
        );

        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categoria>> postCrearCategorias(@Valid @NotNull @RequestBody ArrayList<Map<String, String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ArrayList<Categoria> categorias = new ArrayList<>();

        for (Map<String, String> object : body) {
            categorias.add( new Categoria(
                    object.get("nombre"),
                    dateFormat.format(date),
                    dateFormat.format(date)
            ));
        }

        return ResponseEntity.ok(categoriaService.saveAll(categorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getByIdCategoria(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.findById(id).get());
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> postActualizarCategoria(@Valid @NotNull @RequestBody Map<String, String> body, @PathVariable Integer id) {

        if (!categoriaService.findById(id).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Categoria categoria = categoriaService.findById(id).get();

        categoria.setNombre(body.get("nombre"));
        categoria.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(categoriaService.save(categoria));
    }
}
