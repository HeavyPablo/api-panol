package com.stim.panol.repository;

import com.stim.panol.model.AlarmaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmaStockRepository extends JpaRepository<AlarmaStock, Integer>  {
}
