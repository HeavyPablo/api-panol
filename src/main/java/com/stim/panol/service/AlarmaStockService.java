package com.stim.panol.service;


import com.stim.panol.model.AlarmaStock;
import java.util.List;
import java.util.Optional;

public interface AlarmaStockService {
    List<AlarmaStock> findAll();
    <S extends AlarmaStock> List<S> saveAll(Iterable<S> entities);
    Optional<AlarmaStock> findById(Integer id);
    AlarmaStock save(AlarmaStock alarmaStock);

}
