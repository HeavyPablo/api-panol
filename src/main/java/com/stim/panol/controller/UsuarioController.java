package com.stim.panol.controller;

import com.stim.panol.model.Usuario;
import com.stim.panol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/usuarios/")
    public void saveUsuario(@RequestBody Usuario user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usuarioRepository.save(user);
    }

    @GetMapping("/usuarios/")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{username}")
    public Usuario getUsuario(@PathVariable String username) {
        return usuarioRepository.findByUsername(username);
    }
}
