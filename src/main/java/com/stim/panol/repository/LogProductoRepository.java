package com.stim.panol.repository;

import com.stim.panol.model.LogProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogProductoRepository extends JpaRepository<LogProducto, Integer> {
}
