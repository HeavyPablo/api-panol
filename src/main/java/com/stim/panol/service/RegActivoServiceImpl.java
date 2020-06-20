package com.stim.panol.service;
import com.stim.panol.model.RegActivo;
import com.stim.panol.repository.RegActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RegActivoServiceImpl implements RegActivoService {

    @Autowired
    private RegActivoRepository regActivoRepository;

    @Override
    public List<RegActivo> findAll() {
        return regActivoRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends RegActivo> List<S> saveAll(Iterable<S> entities) {
        return regActivoRepository.saveAll(entities);
    }

    @Override
    public Optional<RegActivo> findById(Integer id) {
        return regActivoRepository.findById(id);
    }

    @Override
    public RegActivo save(RegActivo regActivo) {
        return regActivoRepository.save(regActivo);
    }


}
