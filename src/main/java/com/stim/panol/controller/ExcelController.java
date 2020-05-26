package com.stim.panol.controller;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.stim.panol.model.Alumno;
import com.stim.panol.service.AlumnoServiceImpl;
import com.stim.panol.service.CarreraServiceImpl;

@RestController
public class ExcelController {


    @Autowired
    private CarreraServiceImpl carrerService;

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Alumno>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Alumno> usuList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    Alumno usu = new Alumno();

                    XSSFRow row = worksheet.getRow(index);
                    //Integer id = (int) row.getCell(0).getNumericCellValue();

                    usu.setRut(row.getCell(0).getStringCellValue());
                    usu.setApellidoPaterno(row.getCell(1).getStringCellValue());
                    usu.setApellidoMaterno(row.getCell(2).getStringCellValue());
                    usu.setNombre(row.getCell(3).getStringCellValue());
                    //falta poner la carrera, si es id o nombre completo
                    Integer id = (int) row.getCell(4).getNumericCellValue();
                    usu.setCarrera(carrerService.findById(id).get());
                    //usu.setCarrera(row.getCell(4).getNumericCellValue());
                    usu.setTelefono(row.getCell(5).getStringCellValue());
                    usu.setCorreoAlumno(row.getCell(6).getStringCellValue());
                    usuList.add(usu);
                }
            }

            return new ResponseEntity<>(usuList, status);
        }
}




