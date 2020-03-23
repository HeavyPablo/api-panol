package com.stim.panol.model;


import javax.persistence.*;

@Entity
@Table(name = "ALUMNO")
public class Alumno {

    @Id
    @Column(name = "RUT", updatable = false)
    private String rut;

    @Column(name = "APELLIDO_PATERNO", length = 150, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 150, nullable = false)
    private String apellidoMaterno;

    @Column(name = "NOMBRE", length = 150, nullable = false)
    private String nombre;

    @Column(name = "TELEFONO", length = 20, nullable = false)
    private String telefono;

    @Column(name = "CORREO_ALUMNO", length = 150, nullable = false)
    private String correoAlumno;

    // Relaciones
    @ManyToOne
    private Carrera carrera;

    // Constructores
    public Alumno() {
    }

    public Alumno(String rut, String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, String correoAlumno) {
        this.rut = rut;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoAlumno = correoAlumno;
    }

    // Getters & Setters
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoAlumno() {
        return correoAlumno;
    }

    public void setCorreoAlumno(String correoAlumno) {
        this.correoAlumno = correoAlumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
