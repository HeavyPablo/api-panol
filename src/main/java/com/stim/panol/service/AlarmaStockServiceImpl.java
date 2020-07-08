package com.stim.panol.service;



import org.springframework.stereotype.Service;

import com.stim.panol.model.AlarmaStock;
import com.stim.panol.repository.AlarmaStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlarmaStockServiceImpl implements AlarmaStockService {

    @Autowired
    private AlarmaStockRepository alarmaStockRepository;

    @Override
    public List<AlarmaStock> findAll() {
        return alarmaStockRepository.findAll(Sort.by(Sort.Direction.DESC, "subproductoAS"));
    }


    @Override
    public <S extends AlarmaStock> List<S> saveAll(Iterable<S> entities) {
        return alarmaStockRepository.saveAll(entities);
    }

    @Override
    public Optional<AlarmaStock> findById(Integer id) {
        return alarmaStockRepository.findById(id);
    }

    @Override
    public AlarmaStock save(AlarmaStock alarmaStock) {
        return alarmaStockRepository.save(alarmaStock);
    }

    @Override
    public Optional<AlarmaStock> findByIdEscuelaSAAndIdProductoSA(int idEscuelaSA, int idProductoSA){
        return alarmaStockRepository.findByIdEscuelaSAAndIdProductoSA(idEscuelaSA, idProductoSA);
    }

}
