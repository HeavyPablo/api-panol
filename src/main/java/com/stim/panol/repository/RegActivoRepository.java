package com.stim.panol.repository;

import com.stim.panol.model.Panolero;
import com.stim.panol.model.RegActivo;
import com.stim.panol.model.RegBloqueo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegActivoRepository extends JpaRepository<RegActivo, Integer>  {

}
