package com.stim.panol.service;
import com.stim.panol.model.RegBloqueo;
import com.stim.panol.model.RegDebaja;
import com.stim.panol.repository.RegBloqueoRepository;
import com.stim.panol.repository.RegDebajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RegDebajaServiceImpl implements RegDebajaService {

    @Autowired
    private RegDebajaRepository regDebajaRepository;

    @Override
    public List<RegDebaja> findAll() {
        return regDebajaRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
    }

    @Override
    public <S extends RegDebaja> List<S> saveAll(Iterable<S> entities) {
        return regDebajaRepository.saveAll(entities);
    }

    @Override
    public Optional<RegDebaja> findById(Integer id) {
        return regDebajaRepository.findById(id);
    }

    @Override
    public RegDebaja save(RegDebaja regDebaja) {
        return regDebajaRepository.save(regDebaja);
    }

}
