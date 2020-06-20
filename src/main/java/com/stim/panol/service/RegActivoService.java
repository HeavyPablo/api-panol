package com.stim.panol.service;
import com.stim.panol.model.RegActivo;
import java.util.List;
import java.util.Optional;

public interface RegActivoService {
    List<RegActivo> findAll();
    <S extends RegActivo> List<S> saveAll(Iterable<S> entities);
    Optional<RegActivo> findById(Integer id);
    RegActivo save(RegActivo regActivo);
}
