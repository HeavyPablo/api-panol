package com.stim.panol.controller;

import com.stim.panol.model.Panolero;
import com.stim.panol.model.Producto;
import com.stim.panol.model.Solicitud;
import com.stim.panol.model.Usuario;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Set<Producto> productos = new HashSet<>();
        String estado = "";
        Panolero responsable = null;
        String tipo = "NORMAL";
        String comentario = "";

        for (Map<String, String> objects : body.get("productos")) {
            productos.add(
                    productoService.findById(Integer.parseInt(objects.get("id"))).get()
            );
        }

        if (body.get("solicitud").get(0).containsKey("comentario")) {comentario = body.get("solicitud").get(0).get("comentario");}
        if (body.get("solicitud").get(0).containsKey("tipo")) {
            tipo = body.get("solicitud").get(0).get("tipo");
        }
        if (tipo.equals("NORMAL")) {
            estado = "pendiente";
        } else {
            estado = "esperando";
        }
        if (body.get("solicitud").get(0).containsKey("responsable")) {
            responsable = panoleroService.findByRut(body.get("solicitud").get(0).get("responsable")).get();
        }

        Solicitud solicitud = new Solicitud(
                comentario,
                tipo,
                estado,
                dateFormat.format(date),
                dateFormat.format(date),
                usuarioService.findByUsername(body.get("solicitud").get(0).get("solicitante")).get(),
                responsable,
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
        // los estados son: pendiente, esperando, entregada, completada y descartada
        return ResponseEntity.ok(solicitudService.findByEstado(estado));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Solicitud> postActualizarSolicitud(@Valid @NotNull@ RequestBody Map<String, List<Map<String, String>>> body, @PathVariable Integer id) {
        if (!solicitudService.findById(id).isPresent()) {
            return null;
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Solicitud solicitud = solicitudService.findById(id).get();

        if (!body.get("productos").isEmpty()) {

            Set<Producto> productos = new HashSet<>();

            if (body.get("solicitud").get(0).get("estado").equals("pendiente") || body.get("solicitud").get(0).get("estado").equals("esperando")) {
                for (Map<String, String> objects : body.get("productos")) {
                    Producto producto = productoService.findById(Integer.parseInt(objects.get("id"))).get();
                    productos.add(producto);
                }
                solicitud.setProductos(productos);
            }

            if (body.get("solicitud").get(0).get("estado").equals("entregada")) {
                for (Producto producto : solicitud.getProductos()) {
                    producto.setEstado("enuso");
                    productos.add(producto);
                }
                solicitud.setProductos(productos);
            }

            if (body.get("solicitud").get(0).get("estado").equals("completada")) {
                for (Producto producto : solicitud.getProductos()) {
                    producto.setEstado("disponible");
                    productos.add(producto);
                }
                solicitud.setProductos(productos);
            }
        }

        if (body.get("solicitud").get(0).containsKey("comentario")) solicitud.setComentario(body.get("solicitud").get(0).get("comentario"));
        if (body.get("solicitud").get(0).containsKey("estado")) solicitud.setEstado(body.get("solicitud").get(0).get("estado"));
        if (body.get("solicitud").get(0).containsKey("tipoSolicitud")) solicitud.setTipoSolicitud(body.get("solicitud").get(0).get("tipo"));
        if (body.get("solicitud").get(0).containsKey("responsable")) solicitud.setPanolero(panoleroService.findByRut(body.get("solicitud").get(0).get("responsable")).get());
        if (body.get("solicitud").get(0).containsKey("solicitante")) solicitud.setUsuario(usuarioService.findByUsername(body.get("solicitud").get(0).get("solicitante")).get());
        solicitud.setFechaActualizacion(dateFormat.format(date));

        return ResponseEntity.ok(solicitudService.save(solicitud));
    }

    @RequestMapping("/borrar/{id}")
    public ResponseEntity<Solicitud> postDesabilitarUsuario(@PathVariable Integer id) {
        if (!solicitudService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Solicitud solicitud = solicitudService.findById(id).get();

        if (solicitud.getEstado().equals("pendiente") || solicitud.getEstado().equals("esperando")) {
            solicitud.setEstado("descartada");
            solicitud.setFechaActualizacion(dateFormat.format(date));
        }

        return ResponseEntity.ok(solicitudService.save(solicitud));
    }
}
