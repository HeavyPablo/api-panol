package com.stim.panol.repository;

import com.stim.panol.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByUsername(String username);
}