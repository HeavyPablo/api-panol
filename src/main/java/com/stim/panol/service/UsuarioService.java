package com.stim.panol.service;

import com.stim.panol.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();
    <S extends Usuario> List<S> saveAll(Iterable<S> entities);
    Optional<Usuario> findByUsername(String username);
    Usuario save(Usuario usuario);
    List<Usuario> findByEstado(String estado);
    List<Usuario> findUsersFront();
}
