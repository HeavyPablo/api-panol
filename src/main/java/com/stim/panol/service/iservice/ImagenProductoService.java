package com.stim.panol.service.iservice;

import com.stim.panol.model.ImagenProducto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImagenProductoService {
    Optional<ImagenProducto> findById(int id);
    ImagenProducto storeFile(MultipartFile file) throws Exception;
    ImagenProducto getFile(int id);
    void deleteFile(int id);
}
