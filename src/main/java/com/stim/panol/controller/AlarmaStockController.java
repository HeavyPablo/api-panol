package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.stim.panol.repository.AlarmaStockRepository;

@RestController
@RequestMapping("/alarm")
@Slf4j
public class AlarmaStockController {
    @Autowired
    private AlarmaStockRepository alarmaStockRepository;
    @Autowired
    private AlarmaStockService alarmaStockService;
    @Autowired
    private AlarmaStockServiceImpl alarmaStockServiceImpl;

    @GetMapping
    public ResponseEntity<List<AlarmaStock>> getAllAlarmaStock() {
        return ResponseEntity.ok(alarmaStockService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AlarmaStock postCrearAlarmaStock(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        AlarmaStock alarmaStock = new AlarmaStock(
                body.get("subproductoAS"),
                body.get("escuelaSA"),
                body.get("actualizacionSA"),
                Integer.parseInt(body.get("stock"))

        );

        return  alarmaStockService.save(alarmaStock);
    }

}
