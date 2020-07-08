package com.stim.panol.repository;

import com.stim.panol.model.AlarmaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmaStockRepository extends JpaRepository<AlarmaStock, Integer>  {
    Optional<AlarmaStock> findByIdEscuelaSAAndIdProductoSA (int idEscuelaSA, int idProductoSA);
}
