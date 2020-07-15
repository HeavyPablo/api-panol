package com.stim.panol.service.iservice;

import com.stim.panol.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    List<Director> findAll();
    <S extends Director> List<S> saveAll(Iterable<S> entities);
    Optional<Director> findById(Integer id);
    Director save(Director director);
    Optional<Director> findByRut(String rut);
}
