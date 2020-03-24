package com.stim.panol.repository;

import com.stim.panol.model.Escuela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorios son necesarios para llamar metodos de query como: findAll() -> obtener todos, save() -> guardar o actualizar
@Repository
public interface EscuelaRepository extends JpaRepository<Escuela, Integer> {
}
