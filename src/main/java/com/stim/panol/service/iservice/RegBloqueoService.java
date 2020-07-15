
package com.stim.panol.service.iservice;

import com.stim.panol.model.RegBloqueo;
import java.util.List;
import java.util.Optional;

public interface RegBloqueoService {

    List<RegBloqueo> findAll();
    <S extends RegBloqueo> List<S> saveAll(Iterable<S> entities);
    Optional<RegBloqueo> findById(Integer id);
    RegBloqueo save(RegBloqueo regBloque);

}
