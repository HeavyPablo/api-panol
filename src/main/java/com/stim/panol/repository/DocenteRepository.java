package com.stim.panol.repository;

import com.stim.panol.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    public Optional<Docente> findByRut(String rut);
}
