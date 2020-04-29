package com.stim.panol.controller;

import com.stim.panol.model.Producto;
import com.stim.panol.model.Solicitud;
import com.stim.panol.service.PanoleroServiceImpl;
import com.stim.panol.service.ProductoServiceImpl;
import com.stim.panol.service.SolicitudServiceImpl;
import com.stim.panol.service.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/solicitud")
@Slf4j
public class SolicitudController {

    @Autowired
    private SolicitudServiceImpl solicitudService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PanoleroServiceImpl panoleroService;

    @Autowired
    private ProductoServiceImpl productoService;

    @GetMapping
    public ResponseEntity<List<Solicitud>> getSolicitud() {
        return ResponseEntity.ok(solicitudService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Solicitud> postCrearSolicitud(@Valid @NotNull @RequestBody Map<String, List<Map<String, String>>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Set<Producto> productos = new HashSet<>();

        for (Map<String, String> objects : body.get("productos")) {
            productos.add(
                    productoService.findById(Integer.parseInt(objects.get("id"))).get()
            );
        }

        Solicitud solicitud = new Solicitud(
                body.get("solicitud").get(0).get("comentario"),
                body.get("solicitud").get(0).get("tipo"),
                "pendiente",
                dateFormat.format(date),
                dateFormat.format(date),
                usuarioService.findByUsername(body.get("solicitud").get(0).get("solicitante")).get(),
                panoleroService.findByRut(body.get("solicitud").get(0).get("responsable")).get(),
                productos
        );

        return ResponseEntity.ok(solicitudService.save(solicitud));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getByIdSolicitud(@PathVariable Integer id) {
        if (!solicitudService.findById(id).isPresent()) {
            return null;
        }

        return ResponseEntity.ok(solicitudService.findById(id).get());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Solicitud>> getByEstado(@PathVariable String estado) {
        if (solicitudService.findByEstado(estado).size() <= 0) {
            return null;
        }

        return ResponseEntity.ok(solicitudService.findByEstado(estado));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Solicitud> postActualizarSolicitud(@Valid @NotNull@ RequestBody Map<String, List<Map<String, String>>> body, @PathVariable Integer id) {
        if (!solicitudService.findById(id).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Solicitud solicitud = solicitudService.findById(id).get();

        if (!body.get("productos").isEmpty()) {

            Set<Producto> productos = new HashSet<>();

            if (body.get("solicitud").get(0).get("estado").equals("pendiente")) {
                for (Map<String, String> objects : body.get("productos")) {
                    Producto producto = productoService.findById(Integer.parseInt(objects.get("id"))).get();
                    productos.add(producto);
                }
            } else {
                for (Map<String, String> objects : body.get("productos")) {
                    Producto producto = productoService.findById(Integer.parseInt(objects.get("id"))).get();

                    int cantidadTotal = Integer.parseInt(producto.getCantidad());
                    int cantidadEnUso = 0;

                    if (producto.getCantidadEnUso() != null) {
                        cantidadEnUso = Integer.parseInt(producto.getCantidadEnUso());
                    }

                    if (body.get("solicitud").get(0).get("estado").equals("entregada") && cantidadEnUso < cantidadTotal) {
                        cantidadEnUso += 1;
                        producto.setCantidadEnUso(String.valueOf(cantidadEnUso));
                        productos.add(producto);
                    }

                    if (body.get("solicitud").get(0).get("estado").equals("completada")) {
                        cantidadEnUso -= 1;
                        producto.setCantidadEnUso(String.valueOf(cantidadEnUso));
                        productos.add(producto);
                    }
                }
            }

            if (productos.size() <= 0 || productos.size() != body.get("productos").size()) {
                Solicitud solicitudVacia = new Solicitud();
                solicitudVacia.setComentario("Productos tamaño: " + productos.size() + ", Producto json tamaño: " + body.get("productos").size());
                return ResponseEntity.ok(solicitudVacia);
            }

            solicitud.setProductos(productos);
        }

        if (body.get("solicitud").get(0).containsKey("comentario")) solicitud.setComentario(body.get("solicitud").get(0).get("comentario"));
        if (body.get("solicitud").get(0).containsKey("estado")) solicitud.setEstado(body.get("solicitud").get(0).get("estado"));
        if (body.get("solicitud").get(0).containsKey("tipoSolicitud")) solicitud.setTipoSolicitud(body.get("solicitud").get(0).get("tipo"));
        if (body.get("solicitud").get(0).containsKey("responsable")) solicitud.setPanolero(panoleroService.findByRut(body.get("solicitud").get(0).get("responsable")).get());
        if (body.get("solicitud").get(0).containsKey("solicitante")) solicitud.setUsuario(usuarioService.findByUsername(body.get("solicitud").get(0).get("solicitante")).get());
        solicitud.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(solicitudService.save(solicitud));
    }

}
