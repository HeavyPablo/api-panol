package com.stim.panol.service;

import com.stim.panol.model.LogUsuario;
import com.stim.panol.repository.LogUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogUsuarioServiceImpl implements LogUsuarioService{

    @Autowired
    private LogUsuarioRepository logUsuarioRepository;

    @Override
    public List<LogUsuario> findAll() {
        return logUsuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends LogUsuario> List<S> saveAll(Iterable<S> entities) {
        return logUsuarioRepository.saveAll(entities);
    }

    @Override
    public Optional<LogUsuario> findById(Integer id) {
        return logUsuarioRepository.findById(id);
    }

    @Override
    public LogUsuario save(LogUsuario logUsuario) {
        return logUsuarioRepository.save(logUsuario);
    }

}
