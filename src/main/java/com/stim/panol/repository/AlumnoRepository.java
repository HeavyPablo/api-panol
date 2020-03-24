package com.stim.panol.repository;

import com.stim.panol.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    public Optional<Alumno> findByRut(String rut);
}
