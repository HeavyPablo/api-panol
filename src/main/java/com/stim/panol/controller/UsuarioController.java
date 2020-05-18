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
import java.util.ArrayList;
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
    @Autowired
    private UsuarioService usuarioService;

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        boolean valid = false;

        Usuario newUsuario = new Usuario(
                body.get("username"),
                bCryptPasswordEncoder.encode(body.get("password")),
                body.get("perfil"),
                "activo",
                dateFormat.format(date),
                dateFormat.format(date)
        );

        // Crear datos de perfil
        switch (body.get("perfil")) {
            // Alumno
            case "ALUMNO":
                if (!alumnoService.findByRut(body.get("rut")).isPresent()) {
                    Alumno newAlumno = new Alumno(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoAlumno"),
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
            case "DOCENTE":
                if (!docenteService.findByRut(body.get("rut")).isPresent()) {
                    Docente newDocente = new Docente(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoDocente"),
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
            case "COORDINADOR":
                if (!coordinadorService.findByRut(body.get("rut")).isPresent()) {
                    Coordinador newCoordinador = new Coordinador(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoCoordinador"),
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
            case "DIRECTOR":
                if (!directorService.findByRut(body.get("rut")).isPresent()) {
                    Director newDirector = new Director(
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
                    newDirector = directorService.save(newDirector);
                    newUsuario.setDirector(newDirector);
                    valid = true;
                }
                break;
            // Panolero
            case "PANOLERO":
                if (!panoleroService.findByRut(body.get("rut")).isPresent()) {
                    Panolero newPanolero = new Panolero(
                            body.get("rut"),
                            body.get("apellidoPaterno"),
                            body.get("apellidoMaterno"),
                            body.get("nombre"),
                            body.get("telefono"),
                            body.get("correoPanolero"),
                            dateFormat.format(date),
                            dateFormat.format(date)
                    );
                    newPanolero = panoleroService.save(newPanolero);
                    newUsuario.setPanolero(newPanolero);
                    valid = true;
                }
                break;
            default:
                break;
        }
        if (!valid) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioRepository.save(newUsuario));
    }

    @PostMapping("/usuario/saveAll")
    public ResponseEntity<List<Usuario>> postCrearUsuarios(@Valid @RequestBody @NotNull ArrayList<Map<String, String>> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        boolean valid = false;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        int index = 0;
        for (Map<String, String> object : body) {
            if (!usuarioRepository.findByUsername(object.get("username")).isPresent() || !object.get("perfil").isEmpty()) {
                Usuario usuario = new Usuario(
                        object.get("username"),
                        bCryptPasswordEncoder.encode(object.get("password")),
                        object.get("perfil"),
                        "activo",
                        dateFormat.format(date),
                        dateFormat.format(date)
                );

                switch (object.get("perfil")) {
                    // Alumno
                    case "ALUMNO":
                        if (!alumnoService.findByRut(object.get("rut")).isPresent()) {
                            Alumno newAlumno = new Alumno(
                                    object.get("rut"),
                                    object.get("apellidoPaterno"),
                                    object.get("apellidoMaterno"),
                                    object.get("nombre"),
                                    object.get("telefono"),
                                    object.get("correoAlumno"),
                                    dateFormat.format(date),
                                    dateFormat.format(date),
                                    carreraService.findById(Integer.parseInt(object.get("carrera"))).get()
                            );
                            newAlumno = alumnoService.save(newAlumno);
                            usuario.setAlumno(newAlumno);
                        }
                        break;
                    // Docente
                    case "DOCENTE":
                        if (!docenteService.findByRut(object.get("rut")).isPresent()) {
                            Docente newDocente = new Docente(
                                    object.get("rut"),
                                    object.get("apellidoPaterno"),
                                    object.get("apellidoMaterno"),
                                    object.get("nombre"),
                                    object.get("telefono"),
                                    object.get("correoDocente"),
                                    dateFormat.format(date),
                                    dateFormat.format(date),
                                    escuelaService.findById(Integer.parseInt(object.get("escuela"))).get()
                            );
                            newDocente = docenteService.save(newDocente);
                            usuario.setDocente(newDocente);
                        }
                        break;
                    // Coordinador
                    case "COORDINADOR":
                        if (!coordinadorService.findByRut(object.get("rut")).isPresent()) {
                            Coordinador newCoordinador = new Coordinador(
                                    object.get("rut"),
                                    object.get("apellidoPaterno"),
                                    object.get("apellidoMaterno"),
                                    object.get("nombre"),
                                    object.get("telefono"),
                                    object.get("correoCoordinador"),
                                    dateFormat.format(date),
                                    dateFormat.format(date),
                                    escuelaService.findById(Integer.parseInt(object.get("escuela"))).get()
                            );
                            newCoordinador = coordinadorService.save(newCoordinador);
                            usuario.setCoordinador(newCoordinador);
                        }
                        break;
                    // Director
                    case "DIRECTOR":
                        if (!directorService.findByRut(object.get("rut")).isPresent()) {
                            Director newDirector = new Director(
                                    object.get("rut"),
                                    object.get("apellidoPaterno"),
                                    object.get("apellidoMaterno"),
                                    object.get("nombre"),
                                    object.get("telefono"),
                                    object.get("correoDirector"),
                                    dateFormat.format(date),
                                    dateFormat.format(date),
                                    escuelaService.findById(Integer.parseInt(object.get("escuela"))).get()
                            );
                            newDirector = directorService.save(newDirector);
                            usuario.setDirector(newDirector);
                        }
                        break;
                    // Panolero
                    case "PANOLERO":
                        if (!panoleroService.findByRut(object.get("rut")).isPresent()) {
                            Panolero newPanolero = new Panolero(
                                    object.get("rut"),
                                    object.get("apellidoPaterno"),
                                    object.get("apellidoMaterno"),
                                    object.get("nombre"),
                                    object.get("telefono"),
                                    object.get("correoPanolero"),
                                    dateFormat.format(date),
                                    dateFormat.format(date)
                            );
                            newPanolero = panoleroService.save(newPanolero);
                            usuario.setPanolero(newPanolero);
                        }
                        break;
                    default:
                        break;
                }
                usuarios.add(usuario);
            }
        }

        return ResponseEntity.ok(usuarioRepository.saveAll(usuarios));
    }

    // Obtener todos los usuarios
    @GetMapping("/usuario")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener usuarios por "username"
    @GetMapping("/usuario/username/{username}")
    public Usuario getUsuario(@PathVariable String username) {
        return usuarioRepository.findByUsername(username).get();
    }

    @GetMapping("usuario/perfil/{perfil}")
    public ResponseEntity<List<Usuario>> getUsuarioPerfil(@PathVariable String perfil) {
        if (usuarioRepository.findByPerfil(perfil.toUpperCase()) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(usuarioRepository.findByPerfil(perfil.toUpperCase()));
    }

    @GetMapping("usuario/estado/{estado}")
    public ResponseEntity<List<Usuario>> getUsuarioEstado(@PathVariable String estado) {
        if (usuarioRepository.findByEstado(estado).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(usuarioRepository.findByEstado(estado.toLowerCase()));
    }

    // Actualizar usuario
    @PostMapping("/usuario/{username}")
    public ResponseEntity<Usuario> postActualizarUsuario(@Valid @RequestBody @NotNull Map<String, String> body, @PathVariable String username) {
        if (!usuarioRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Usuario usuario = usuarioRepository.findByUsername(username.toLowerCase()).get();
        //usuario.setPassword(bCryptPasswordEncoder.encode(body.get("password")));
        usuario.setFechaActualizacion(dateFormat.format(date));
        usuario.setPerfil(body.get("perfil"));
        usuario.setUsername(body.get("username"));
        usuario.setEstado(body.get("estado"));


        
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    // Actualizar usuario segun el estado
    @PostMapping("/usuario/ActEst/{username}")
    public ResponseEntity<Usuario> postActualizarEstado(@Valid @RequestBody @NotNull Map<String, String> body, @PathVariable String username) {
        if (!usuarioRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Usuario usuario = usuarioRepository.findByUsername(username.toLowerCase()).get();
        usuario.setFechaActualizacion(dateFormat.format(date));
        usuario.setUsername(body.get("username"));
        usuario.setEstado(body.get("estado"));
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @GetMapping("/usuario/front")
    public ResponseEntity<List<Usuario>> getUsuariosFront() {
        return ResponseEntity.ok(usuarioService.findUsersFront());
    }
}
