package com.stim.panol.repository;

import com.stim.panol.model.Coordinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinadorRepository extends JpaRepository<Coordinador, Integer> {
    public Optional<Coordinador> findByRut(String rut);
}
