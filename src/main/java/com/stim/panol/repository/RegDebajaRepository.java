package com.stim.panol.repository;

import com.stim.panol.model.RegDebaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegDebajaRepository extends JpaRepository<RegDebaja, Integer>  {
}
