package com.stim.panol.repository;

import com.stim.panol.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositorios son necesarios para llamar metodos de query como: findAll() -> obtener todos, save() -> guardar o actualizar..  (Se prepara la query)
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    // Crear metodos para utilizarlos como WHERE en el query como: finByRut() -> SELECT * WHERE RUT = ... || findById() -> no es necesario crear metodo, ya viene incorporado.
    public Optional<Alumno> findByRut(String rut);
}
