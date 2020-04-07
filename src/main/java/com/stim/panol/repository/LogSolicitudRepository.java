package com.stim.panol.repository;

import com.stim.panol.model.LogSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogSolicitudRepository extends JpaRepository<LogSolicitud, Integer> {
}
