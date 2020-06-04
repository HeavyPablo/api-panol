package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.service.*;
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
    private EscuelaServiceImpl escuelaService;

    @Autowired
    private SubcategoriaServiceImpl subcategoriaService;

    @Autowired
    private SolicitudServiceImpl solicitudService;

    @GetMapping
    public ResponseEntity<List<Producto>> getProducto() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Producto> postCrearProducto(@RequestParam(value = "body") String json,
                                                      @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> body = mapper.readValue(json, Map.class);

        System.out.println(body.get("nombre"));

        Producto producto = new Producto(
                body.get("nombre"),
                "disponible",
                body.get("descripcion"),
                dateFormat.format(date),
                dateFormat.format(date),
                escuelaService.findById(Integer.parseInt(body.get("escuela"))).get(),
                subcategoriaService.findById(Integer.parseInt(body.get("subcategoria"))).get()
        );

        ImagenProducto imagen = imagenProductoService.storeFile(file);

        producto.setImagenProducto(imagen);

        return ResponseEntity.ok(productoService.save(producto));
    }

    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> postCrearProductos(@Valid @NotNull @RequestBody ArrayList<Map<String, String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ArrayList<Producto> productos = new ArrayList<>();

        for (Map<String, String> object : body) {
            productos.add( new Producto(
                    object.get("nombre"),
                    "disponible",
                    object.get("descripcion"),
                    dateFormat.format(date),
                    dateFormat.format(date),
                    escuelaService.findById(Integer.parseInt(object.get("escuela"))).get(),
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

    @PostMapping(value = "/{id}")
    public ResponseEntity<Producto> postActualizarProducto(@RequestParam(value = "file", required = false) MultipartFile file,
                                                           @RequestParam(value = "body") String json,
                                                           @PathVariable Integer id) throws Exception {

        if (!productoService.findById(id).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> body = mapper.readValue(json, Map.class);

        Producto producto = productoService.findById(id).get();

        int imagenIdOld = producto.getImagenProducto().getId();

        if (file != null) {
            ImagenProducto imagen = imagenProductoService.storeFile(file);
            producto.setImagenProducto(imagen);
        }

        if (body.containsKey("nombre")) { producto.setNombre(body.get("nombre")); }
        if (body.containsKey("descripcion")) { producto.setDescripcion(body.get("descripcion")); }
        if (body.containsKey("estado")) { producto.setEstado(body.get("estado")); }
        producto.setFechaActualizacion(dateFormat.format(date));
        if (body.containsKey("escuela")) { producto.setEscuela(escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()); }
        if (body.containsKey("subcategoria")) { producto.setSubcategoria(subcategoriaService.findById(Integer.parseInt(body.get("subcategoria"))).get()); }

        productoService.save(producto);
        if (file != null) {
            imagenProductoService.deleteFile(imagenIdOld);
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<Producto> deleteByIdProducto(@PathVariable Integer id) {
        if (!productoService.findById(id).isPresent()) {
            return null;
        }
        productoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filtro/{filtro}")
    public ResponseEntity<List<Producto>> findByEstado(@PathVariable String filtro) {
        if (filtro.equals("todos")) {
            return ResponseEntity.ok(productoService.findAll());
        }

        // STOCK NORMAL = si los productos por subcategoria son mayor o igual a 8
        if (filtro.equals("stocknormal")) {
            List<Subcategoria> subcategorias = subcategoriaService.findAll();
            List<Producto> productos = productoService.findAll();
            List<Producto> productosStockNormal = new ArrayList<>();

            for (Subcategoria subcategoria : subcategorias) {
                List<Producto> productoPorSubcategoria = new ArrayList<>();
                for (Producto producto : productos) {
                    if (producto.getSubcategoria().getId() == subcategoria.getId() && !producto.getEstado().equals("debaja")) {
                        productoPorSubcategoria.add(producto);
                    }
                }
                if (productoPorSubcategoria.size() >= 8) {
                    productosStockNormal.addAll(productoPorSubcategoria);
                }
            }
            return ResponseEntity.ok(productosStockNormal);
        }

        // STOCK BAJO = Si los productos por subcategoria son mayor a 3 y menor a 8
        if (filtro.equals("stockbajo")) {
            List<Subcategoria> subcategorias = subcategoriaService.findAll();
            List<Producto> productos = productoService.findAll();
            List<Producto> productosStockBajo = new ArrayList<>();

            for (Subcategoria subcategoria : subcategorias) {
                List<Producto> productoPorSubcategoria = new ArrayList<>();
                for (Producto producto : productos) {
                    if (producto.getSubcategoria().getId() == subcategoria.getId() && !producto.getEstado().equals("debaja")) {
                        productoPorSubcategoria.add(producto);
                    }
                }
                if (3 < productoPorSubcategoria.size() && productoPorSubcategoria.size() < 8) {
                    productosStockBajo.addAll(productoPorSubcategoria);
                }
            }
            return ResponseEntity.ok(productosStockBajo);
        }
        // STOCK CRITICO = Si los productos por subcategoria son menor o igual a 3
        if (filtro.equals("stockcritico")) {
            List<Subcategoria> subcategorias = subcategoriaService.findAll();
            List<Producto> productos = productoService.findAll();
            List<Producto> productosStockCritico = new ArrayList<>();

            for (Subcategoria subcategoria : subcategorias) {
                List<Producto> productoPorSubcategoria = new ArrayList<>();
                for (Producto producto : productos) {
                    if (producto.getSubcategoria().getId() == subcategoria.getId() && !producto.getEstado().equals("debaja")) {
                        productoPorSubcategoria.add(producto);
                    }
                }
                if (productoPorSubcategoria.size() <= 3) {
                    productosStockCritico.addAll(productoPorSubcategoria);
                }
            }
            return ResponseEntity.ok(productosStockCritico);
        }

        // Estados de producto: enuso, disponible, debaja
        return ResponseEntity.ok(productoService.findByEstado(filtro));
    }
}
