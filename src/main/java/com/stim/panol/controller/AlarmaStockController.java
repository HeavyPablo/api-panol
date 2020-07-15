package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.service.*;
import com.stim.panol.service.iservice.AlarmaStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
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

    //trae a todos las tablas
    @GetMapping
    public ResponseEntity<List<AlarmaStock>> getAllAlarmaStock() {
        return ResponseEntity.ok(alarmaStockService.findAll());
    }


    @GetMapping("/todos")
    public ResponseEntity<List<AlarmaStock>> getAllAlarmaStockNotif() throws ParseException {
        List<AlarmaStock> todo = alarmaStockService.findAll();

        ArrayList<AlarmaStock> envio = new ArrayList<>();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (AlarmaStock p:
             todo) {
            Date actualProducto = dateFormat.parse(p.getActualizacionSA());
            long milliseconds = date.getTime() - actualProducto.getTime();
            int stockTotal =  p.getStockTotal();
            if( milliseconds >= 30000 && milliseconds <= 40000 && stockTotal != 0){
                envio.add(p);
            }

        }
        if(envio == null){
            return ResponseEntity.ok(null);
        }else{
            return ResponseEntity.ok(envio);
        }

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AlarmaStock postCrearAlarmaStock(@Valid @NotNull @RequestBody Map<String, String> body) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        AlarmaStock alarmaStock = new AlarmaStock(
                body.get("subproductoAS"),
                body.get("escuelaSA"),
                body.get("actualizacionSA"),
                Integer.parseInt(body.get("stock")),
                Integer.parseInt(body.get("idEscuelaSA")),
                Integer.parseInt(body.get("idProductoSA")),
                Integer.parseInt(body.get("stockTotal"))
        );

        return  alarmaStockService.save(alarmaStock);
    }

    

    
}
