package com.stim.panol.controller;

import com.stim.panol.model.LogProducto;
import com.stim.panol.service.LogProductoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log-producto")
@Slf4j
public class LogProductoController {

    @Autowired
    private LogProductoServiceImpl logProductoService;

    @GetMapping
    public ResponseEntity<List<LogProducto>> getAllProductos() {
        return ResponseEntity.ok(logProductoService.findAll());
    }
}
