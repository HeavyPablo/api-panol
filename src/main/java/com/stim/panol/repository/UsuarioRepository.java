package com.stim.panol.repository;

import com.stim.panol.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByPerfil(String perfil);
    List<Usuario> findByEstado(String estado);
    List<Usuario> findByPerfilIn(List<String> perfiles);
}
