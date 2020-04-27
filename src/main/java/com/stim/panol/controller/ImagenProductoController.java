package com.stim.panol.controller;

import com.stim.panol.model.ImagenProducto;
import com.stim.panol.service.ImagenProductoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/imagen/producto")
@Slf4j
public class ImagenProductoController {

    @Autowired
    private ImagenProductoServiceImpl imagenProductoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagenProducto> postCrearImagenProducto(@RequestParam MultipartFile file) throws Exception {
        return ResponseEntity.ok(imagenProductoService.storeFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenProducto> getImagenProducto(@PathVariable int id) {
        return ResponseEntity.ok(imagenProductoService.getFile(id));
    }
}
