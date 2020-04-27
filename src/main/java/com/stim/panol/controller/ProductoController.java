package com.stim.panol.controller;

import com.stim.panol.model.ImagenProducto;
import com.stim.panol.model.Producto;
import com.stim.panol.service.ImagenProductoServiceImpl;
import com.stim.panol.service.ProductoServiceImpl;
import com.stim.panol.service.SubcategoriaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/producto")
@Slf4j
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private ImagenProductoServiceImpl imagenProductoService;

    @Autowired
    private SubcategoriaServiceImpl subcategoriaService;

    @GetMapping
    public ResponseEntity<List<Producto>> getProducto() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Producto> postCrearProducto(@RequestParam(value = "body") String json,
                                                      @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> body = mapper.readValue(json, Map.class);

        System.out.println(body.get("nombre"));

        Producto producto = new Producto(
                body.get("nombre"),
                "nuevo",
                body.get("descripcion"),
                body.get("cantidad"),
                dateFormat.format(date),
                dateFormat.format(date),
                subcategoriaService.findById(Integer.parseInt(body.get("subcategoria"))).get()
        );

        ImagenProducto imagen = imagenProductoService.storeFile(file);

        producto.setImagenProducto(imagen);

        return ResponseEntity.ok(productoService.save(producto));
    }

    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> postCrearProductos(@Valid @NotNull @RequestBody ArrayList<Map<String, String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        ArrayList<Producto> productos = new ArrayList<>();

        for (Map<String, String> object : body) {
            productos.add( new Producto(
                    object.get("nombre"),
                    "nuevo",
                    object.get("descripcion"),
                    object.get("cantidad"),
                    dateFormat.format(date),
                    dateFormat.format(date),
                    subcategoriaService.findById(Integer.parseInt(object.get("subcategoria"))).get()
            ));
        }

        return ResponseEntity.ok(productoService.saveAll(productos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getByIdProducto(@PathVariable Integer id) {
        if (!productoService.findById(id).isPresent()) {
            return null;
        }

        return ResponseEntity.ok(productoService.findById(id).get());
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> postActualizarProducto(@Valid @NotNull @RequestBody  Map<String, String> body, @PathVariable Integer id) {
        if (!productoService.findById(id).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Producto producto = productoService.findById(id).get();
        Producto newPrducto = new Producto();

        newPrducto.setNombre(body.get("nombre"));
        newPrducto.setDescripcion(body.get("descripcion"));
        newPrducto.setEstado(body.get("estado"));
        newPrducto.setFechaActualizacion(dateFormat.format(date));
        newPrducto.setSubcategoria(subcategoriaService.findById(Integer.parseInt(body.get("subcategoria"))).get());

        return ResponseEntity.ok(productoService.save(producto));
    }
}
