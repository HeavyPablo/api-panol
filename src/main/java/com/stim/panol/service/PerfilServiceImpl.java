package com.stim.panol.service;

import com.stim.panol.model.Perfil;
import com.stim.panol.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Override
    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    @Override
    public <S extends Perfil> List<S> saveAll(Iterable<S> entities) {
        return perfilRepository.saveAll(entities);
    }

    @Override
    public Optional<Perfil> findById(Integer id) {
        return perfilRepository.findById(id);
    }

    @Override
    public Perfil save(Perfil perfil) {
        return perfilRepository.save(perfil);
    }
}
