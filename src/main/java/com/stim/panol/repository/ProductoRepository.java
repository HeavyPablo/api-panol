package com.stim.panol.repository;

import com.stim.panol.model.Escuela;
import com.stim.panol.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByEstado(String estado);
    Optional<List<Producto>> findByEscuelaAndEstado(Escuela escuela, String estado);

    List<Producto> findByEscuela(Escuela escuela);
}
