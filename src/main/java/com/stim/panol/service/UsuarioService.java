package com.stim.panol.service;

import com.stim.panol.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();
    <S extends Usuario> List<S> saveAll(Iterable<S> entities);
    Usuario findByUsername(String username);
    Usuario save(Usuario usuario);
}
