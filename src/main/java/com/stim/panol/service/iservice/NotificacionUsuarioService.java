package com.stim.panol.service.iservice;

import com.stim.panol.model.NotificacionUsuario;
import com.stim.panol.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface NotificacionUsuarioService {

    List<NotificacionUsuario> findAll();
    Optional<NotificacionUsuario> findById(int id);
    Optional<NotificacionUsuario> findByUsuario(Usuario usuario);
    NotificacionUsuario save(NotificacionUsuario notificacionUsuario);
}
