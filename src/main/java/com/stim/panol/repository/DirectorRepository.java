package com.stim.panol.repository;

import com.stim.panol.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    public Optional<Director> findByRut(String rut);
}
