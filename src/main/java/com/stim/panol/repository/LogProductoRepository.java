package com.stim.panol.repository;

import com.stim.panol.model.LogProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogProductoRepository extends JpaRepository<LogProducto, Integer> {
    List<LogProducto> findByEstadoOrderByFechaDesc(String estado);
    List<LogProducto> findByOperacionOrderByFechaDesc(String operacion);
}
