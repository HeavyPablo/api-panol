package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.service.*;
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

    @Autowired
    private LogSolicitudServiceImpl logSolicitudService;

    @Autowired
    private LogProductoServiceImpl logProductoService;

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
        String diasSolicitados = "";
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

        if (estado.equals("esperando") && body.get("solicitud").get(0).containsKey("diasSolicitados")) {
            diasSolicitados = body.get("solicitud").get(0).get("diasSolicitados");
            solicitud.setDiasSolicitados(diasSolicitados);
        }

        solicitud = solicitudService.save(solicitud);



        if (solicitud != null) {

            int escuelaSolicitante =  0;
            if (solicitud.getUsuario().getAlumno() != null) {
                escuelaSolicitante = solicitud.getUsuario().getAlumno().getCarrera().getEscuela().getId();
            } else {
                escuelaSolicitante = solicitud.getUsuario().getDocente().getEscuela().getId();
            }

            int idResponsable = 0;
            idResponsable = Integer.parseInt(body.get("solicitud").get(0).get("logResponsable"));

            LogSolicitud logSolicitud = new LogSolicitud(
                    "crear",
                    solicitud.getId(),
                    solicitud.getEstado(),
                    solicitud.getUsuario().getId(),
                    solicitud.getTipoSolicitud(),
                    escuelaSolicitante,
                    idResponsable,
                    solicitud.getFechaActualizacion(),
                    solicitud.getFechaCreacion()
            );

            logSolicitudService.save(logSolicitud);
            return ResponseEntity.ok(solicitud);
        }

        return ResponseEntity.badRequest().build();
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

        int idResponsable = 0;
        idResponsable = Integer.parseInt(body.get("solicitud").get(0).get("logResponsable"));

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

                    LogProducto logProducto = new LogProducto(
                            "Producto en uso de " + producto.getEscuela().getNombre(),
                            producto.getId(),
                            "actualizar",
                            producto.getEstado(),
                            producto.getEscuela().getId(),
                            idResponsable,
                            dateFormat.format(date)
                    );

                    logProductoService.save(logProducto);
                }
                solicitud.setProductos(productos);
            }

            if (body.get("solicitud").get(0).get("estado").equals("completada")) {
                for (Producto producto : solicitud.getProductos()) {
                    producto.setEstado("disponible");
                    productos.add(producto);

                    LogProducto logProducto = new LogProducto(
                            "Producto devuelto de " + producto.getEscuela().getNombre(),
                            producto.getId(),
                            "actualizar",
                            producto.getEstado(),
                            producto.getEscuela().getId(),
                            idResponsable,
                            dateFormat.format(date)
                    );

                    logProductoService.save(logProducto);
                }
                solicitud.setProductos(productos);
            }
        }

        if (body.get("solicitud").get(0).containsKey("comentario")) solicitud.setComentario(body.get("solicitud").get(0).get("comentario"));
        if (body.get("solicitud").get(0).containsKey("estado")) solicitud.setEstado(body.get("solicitud").get(0).get("estado"));
        if (body.get("solicitud").get(0).containsKey("diasSolicitados")) solicitud.setDiasSolicitados(body.get("solicitud").get(0).get("diasSolicitados"));
        if (body.get("solicitud").get(0).containsKey("tipoSolicitud")) solicitud.setTipoSolicitud(body.get("solicitud").get(0).get("tipo"));
        if (body.get("solicitud").get(0).containsKey("responsable")) solicitud.setPanolero(panoleroService.findByRut(body.get("solicitud").get(0).get("responsable")).get());
        if (body.get("solicitud").get(0).containsKey("solicitante")) solicitud.setUsuario(usuarioService.findByUsername(body.get("solicitud").get(0).get("solicitante")).get());
        solicitud.setFechaActualizacion(dateFormat.format(date));

        solicitud = solicitudService.save(solicitud);

        if (solicitud != null) {
            int escuelaSolicitante =  0;
            if (solicitud.getUsuario().getAlumno() != null) {
                escuelaSolicitante = solicitud.getUsuario().getAlumno().getCarrera().getEscuela().getId();
            } else {
                escuelaSolicitante = solicitud.getUsuario().getDocente().getEscuela().getId();
            }

            LogSolicitud logSolicitud = new LogSolicitud(
                    "actualizar",
                    solicitud.getId(),
                    solicitud.getEstado(),
                    solicitud.getUsuario().getId(),
                    solicitud.getTipoSolicitud(),
                    escuelaSolicitante,
                    idResponsable,
                    solicitud.getFechaActualizacion(),
                    solicitud.getFechaCreacion()
            );

            logSolicitudService.save(logSolicitud);
            return ResponseEntity.ok(solicitud);
        }

        return ResponseEntity.badRequest().build();
    }

    @RequestMapping("/borrar/{id}")
    public ResponseEntity<Solicitud> postDescartarSolicitud(@PathVariable Integer id, @RequestBody Map<String, String> body) {
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

        solicitud = solicitudService.save(solicitud);

        if (solicitud != null) {

            int escuelaSolicitante =  0;
            if (solicitud.getUsuario().getAlumno() != null) {
                escuelaSolicitante = solicitud.getUsuario().getAlumno().getCarrera().getEscuela().getId();
            } else {
                escuelaSolicitante = solicitud.getUsuario().getDocente().getEscuela().getId();
            }

            int idResponsable = 0;
            idResponsable = Integer.parseInt(body.get("logResponsable"));
            LogSolicitud logSolicitud = new LogSolicitud(
                    "deshabilitar",
                    solicitud.getId(),
                    solicitud.getEstado(),
                    solicitud.getUsuario().getId(),
                    solicitud.getTipoSolicitud(),
                    escuelaSolicitante,
                    idResponsable,
                    solicitud.getFechaActualizacion(),
                    solicitud.getFechaCreacion()
            );

            logSolicitudService.save(logSolicitud);
            return ResponseEntity.ok(solicitud);
        }

        return ResponseEntity.badRequest().build();
    }
}
