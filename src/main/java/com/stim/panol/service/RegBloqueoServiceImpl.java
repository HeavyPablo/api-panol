package com.stim.panol.service;

import com.stim.panol.model.RegBloqueo;
import com.stim.panol.repository.RegBloqueoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RegBloqueoServiceImpl implements RegBloqueoService{

    @Autowired
    private RegBloqueoRepository regBloqueoRepository;

    @Override
    public List<RegBloqueo> findAll() {
        return regBloqueoRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends RegBloqueo> List<S> saveAll(Iterable<S> entities) {
        return regBloqueoRepository.saveAll(entities);
    }

    @Override
    public Optional<RegBloqueo> findById(Integer id) {
        return regBloqueoRepository.findById(id);
    }

    @Override
    public RegBloqueo save(RegBloqueo regBloqueo) {
        return regBloqueoRepository.save(regBloqueo);
    }

}
