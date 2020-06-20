package com.stim.panol.controller;

import com.stim.panol.model.*;
import com.stim.panol.repository.RegBloqueoRepository;
import com.stim.panol.repository.UsuarioRepository;
import com.stim.panol.service.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.stim.panol.repository.RegActivoRepository;
@RestController
public class RegActivoController {
    @Autowired
    private RegActivoRepository regActivoRepository;

    @Autowired
    private RegActivoService regActivoService;

    @Autowired
    private RegActivoServiceImpl regActivoServiceImpl;

    @PostMapping("/regactivo")
    public ResponseEntity<RegActivo> postRegActivo(@Valid @RequestBody @NotNull Map<String, String> body) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        RegActivo activado = new RegActivo(
                Integer.parseInt(body.get("idUsuarioRA")),
                body.get("nomUsuarioRA"),
                body.get("perfilRA"),
                body.get("estadoRA"),
                Integer.parseInt(body.get("idResponsableRA")),
                body.get("nomResponsableRA"),
                body.get("perfilResponsableRA"),
                body.get("escuelaRA"),
                body.get("fechaCreacionUsuarioRA"),
                dateFormat.format(date),
                body.get("carreraActivada"),
                body.get("justificacion")
        );

        return ResponseEntity.ok(regActivoRepository.save(activado));
    }
}
