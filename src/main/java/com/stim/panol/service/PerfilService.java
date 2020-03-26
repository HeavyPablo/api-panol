package com.stim.panol.service;

import com.stim.panol.model.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilService {

    List<Perfil> findAll();
    <S extends Perfil> List<S> saveAll(Iterable<S> entities);
    Optional<Perfil> findById(Integer id);
    Perfil save(Perfil perfil);
}
