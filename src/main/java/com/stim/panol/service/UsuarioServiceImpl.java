package com.stim.panol.service;

import com.stim.panol.model.Usuario;
import com.stim.panol.repository.UsuarioRepository;
import com.stim.panol.service.iservice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends Usuario> List<S> saveAll(Iterable<S> entities) {
        return usuarioRepository.saveAll(entities);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findByEstado(String estado) {
        return usuarioRepository.findByEstado(estado);
    }

    @Override
    public List<Usuario> findUsersFront() {
        ArrayList<String> perfiles = new ArrayList<>();
        perfiles.add("ALUMNO");
        perfiles.add("DOCENTE");
        return usuarioRepository.findByPerfilIn(perfiles);
    }
}
