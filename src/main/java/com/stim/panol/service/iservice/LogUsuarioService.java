package com.stim.panol.service.iservice;

import com.stim.panol.model.LogUsuario;

import java.util.List;
import java.util.Optional;

public interface LogUsuarioService {
    List<LogUsuario> findAll();
    <S extends LogUsuario> List<S> saveAll(Iterable<S> entities);
    Optional<LogUsuario> findById(Integer id);
    LogUsuario save(LogUsuario logUsuario);
}
