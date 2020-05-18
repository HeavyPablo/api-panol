package com.stim.panol.service;

import com.stim.panol.model.Director;
import com.stim.panol.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService{

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends Director> List<S> saveAll(Iterable<S> entities) {
        return directorRepository.saveAll(entities);
    }

    @Override
    public Optional<Director> findById(Integer id) {
        return directorRepository.findById(id);
    }

    @Override
    public Director save(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Optional<Director> findByRut(String rut) {
        return directorRepository.findByRut(rut);
    }
}
