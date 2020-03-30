package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.repository.UsuarioRepository;
import com.stim.panol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AlumnoServiceImpl alumnoService;
    @Autowired
    private PerfilServiceImpl perfilService;
    @Autowired
    private DocenteServiceImpl docenteService;
    @Autowired
    private CoordinadorServiceImpl coordinadorService;
    @Autowired
    private DirectorServiceImpl directorService;
    @Autowired
    private PanoleroServiceImpl panoleroService;
    @Autowired
    private CarreraServiceImpl carreraService;
    @Autowired
    private EscuelaServiceImpl escuelaService;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Crear usuario -> debe recibir {username, password, perfil} y los datos de los perfiles
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody @NotNull Map<String, String> body) {

        if (body.get("perfil").isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        boolean valid = false;

        Usuario newUsuario = new Usuario(
                body.get("username"),
                bCryptPasswordEncoder.encode(body.get("password")),
                dateFormat.format(date),
                dateFormat.format(date),
                perfilService.findById(Integer.parseInt(body.get("perfil"))).get(),
                null,
                null,
                null,
                null,
                null
        );

        // Crear datos de perfil (Tablas segun el perfil)
        switch (body.get("perfil")) {
            // Alumno
            case "2":
                if (!alumnoService.findByRut(body.get("rut")).isPresent()) {
                    Alumno newAlumno = new Alumno(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoAlumno"),
                            "activo",
                            dateFormat.format(date),
                            dateFormat.format(date),
                            carreraService.findById(Integer.parseInt(body.get("carrera"))).get()
                    );
                    newAlumno = alumnoService.save(newAlumno);
                    newUsuario.setAlumno(newAlumno);
                    valid = true;
                }

                break;
            // Docente
            case "3":
                if (!docenteService.findByRut(body.get("rut")).isPresent()) {
                    Docente newDocente = new Docente(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoDocente"),
                            "activo",
                            dateFormat.format(date),
                            dateFormat.format(date),
                            escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()
                    );
                    newDocente = docenteService.save(newDocente);
                    newUsuario.setDocente(newDocente);
                    valid = true;
                }
                break;
            // Coordinador
            case "4":
                if (!coordinadorService.findByRut(body.get("rut")).isPresent()) {
                    Coordinador newCoordinador = new Coordinador(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoCoordinador"),
                            "activo",
                            dateFormat.format(date),
                            dateFormat.format(date),
                            escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()
                    );
                    newCoordinador = coordinadorService.save(newCoordinador);
                    newUsuario.setCoordinador(newCoordinador);
                    valid = true;
                }
                break;
            // Director
            case "5":
                if (!directorService.findByRut(body.get("rut")).isPresent()) {
                    Director newDirector = new Director(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoDirector"),
                            "activo",
                            dateFormat.format(date),
                            dateFormat.format(date),
                            escuelaService.findById(Integer.parseInt(body.get("escuela"))).get()
                    );
                    newDirector = directorService.save(newDirector);
                    newUsuario.setDirector(newDirector);
                    valid = true;
                }
                break;
            // Panolero
            case "6":
                if (!panoleroService.findByRut(body.get("rut")).isPresent()) {
                    Panolero newPanolero = new Panolero(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoPanolero"),
                            "activo",
                            dateFormat.format(date),
                            dateFormat.format(date)
                    );
                    newPanolero = panoleroService.save(newPanolero);
                    newUsuario.setPanolero(newPanolero);
                    valid = true;
                }
            default:
                break;
        }
        if (!valid) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioRepository.save(newUsuario));
    }

    // Obtener todos los usuarios
    @GetMapping("/usuario")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener usuarios por "username"
    @GetMapping("/usuario/{username}")
    public Usuario getUsuario(@PathVariable String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Actualizar usuario
    @PostMapping("/usuario/{username}")
    public ResponseEntity<Usuario> postActualizarUsuario(@Valid @RequestBody @NotNull Map<String, String> body, @PathVariable String username) {
        if (usuarioRepository.findByUsername(username) == null) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Usuario usuario = usuarioRepository.findByUsername(username);
        usuario.setPassword(bCryptPasswordEncoder.encode(body.get("password")));
        usuario.setFechaActualizacion(dateFormat.format(date));
        usuario.setPerfil(perfilService.findById(Integer.parseInt(body.get("perfil"))).get());
        usuario.setUsername(body.get("username"));
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }
}
