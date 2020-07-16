package com.stim.panol.repository;

import com.stim.panol.model.NotificacionUsuario;
import com.stim.panol.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacionUsuarioRepository extends JpaRepository<NotificacionUsuario, Integer> {
    Optional<List<NotificacionUsuario>> findByUsuarioOrderByFechaDesc(Usuario usuario);
}
