package com.stim.panol.service;

import com.stim.panol.model.ImagenProducto;
import com.stim.panol.repository.ImagenProductoRepository;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImagenProductoServiceImpl implements ImagenProductoService{

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    @Override
    public Optional<ImagenProducto> findById(int id) {
        return imagenProductoRepository.findById(id);
    }

    @Override
    public ImagenProducto storeFile(MultipartFile file) throws Exception {

        String nombre = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            ImagenProducto imagenProducto = new ImagenProducto(file.getBytes(), nombre, file.getContentType());

            return imagenProductoRepository.save(imagenProducto);
        } catch (IOException e) {
            throw new Exception("Error al obtener la imagen " + nombre, e);
        }
    }

    @Override
    public ImagenProducto getFile(int id) {
        return imagenProductoRepository.findById(id).get();
    }
}
