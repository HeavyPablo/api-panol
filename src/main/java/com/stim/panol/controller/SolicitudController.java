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
    public ResponseEntity<Solicitud> postCrearSolicitud(@Valid @NotNull @RequestBody Map<String, List<String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


        List<String> packProductos = new ArrayList<>(Arrays.asList(body.get("productos").get(0).split(",")));
        Map<String, String> mapProductos = new HashMap<>();

        Set<Producto> productos = new HashSet<>();

        for (String pack : packProductos) {
            pack = pack.substring(1, pack.length() - 1);
            String[] entry = pack.split("=");

            productos.add(productoService.findById(Integer.parseInt(entry[1].trim())).get());
        }

        Solicitud solicitud = new Solicitud(
                body.get("comentario"),
                body.get("tipo"),
                "pendiente",
                dateFormat.format(date),
                dateFormat.format(date),
                usuarioService.findByUsername(body.get("solicitante")).get(),
                panoleroService.findByRut(body.get("responsable")).get(),
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
}
