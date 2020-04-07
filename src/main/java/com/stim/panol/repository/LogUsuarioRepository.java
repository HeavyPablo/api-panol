package com.stim.panol.repository;

import com.stim.panol.model.LogUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogUsuarioRepository extends JpaRepository<LogUsuario, Integer> {
}
