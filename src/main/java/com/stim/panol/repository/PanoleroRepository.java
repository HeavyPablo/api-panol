package com.stim.panol.repository;

import com.stim.panol.model.Panolero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanoleroRepository extends JpaRepository<Panolero, Integer> {
    Optional<Panolero> findByRut(String rut);
}
