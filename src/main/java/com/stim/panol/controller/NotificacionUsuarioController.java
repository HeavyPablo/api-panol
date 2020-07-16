package com.stim.panol.controller;

import com.stim.panol.model.NotificacionUsuario;
import com.stim.panol.model.Usuario;
import com.stim.panol.service.NotificacionUsuarioServiceImpl;
import com.stim.panol.service.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificacion/usuario")
@Slf4j
public class NotificacionUsuarioController {

    @Autowired
    NotificacionUsuarioServiceImpl notificacionUsuarioService;
    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/{usernameUsuario}")
    public ResponseEntity<List<NotificacionUsuario>> getNotificacionesByIdUsuario(@PathVariable String usernameUsuario) {
        Optional<Usuario> usuario = usuarioService.findByUsername(usernameUsuario);
        return usuario.map(value -> ResponseEntity.ok(notificacionUsuarioService.findByUsuario(value).get())).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
