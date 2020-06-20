package com.stim.panol.service;

import com.stim.panol.model.RegDebaja;
import java.util.List;
import java.util.Optional;

public interface RegDebajaService {

    List<RegDebaja> findAll();
    <S extends RegDebaja> List<S> saveAll(Iterable<S> entities);
    Optional<RegDebaja> findById(Integer id);
    RegDebaja save(RegDebaja regDebaja);
}
