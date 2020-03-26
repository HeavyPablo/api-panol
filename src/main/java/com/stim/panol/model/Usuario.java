package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERNAME", length = 150, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FECHA_CREACION", nullable = false)
    private String fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private String fechaActualizacion;

    // Relaciones
    @ManyToOne
    private Perfil perfil;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Docente docente;

    @ManyToOne
    private Director director;

    @ManyToOne
    private Coordinador coordinador;

    @ManyToOne
    private Panolero panolero;

    // Constructores
    public Usuario() {
    }

    public Usuario(String username, String password, String fechaCreacion, String fechaActualizacion, Perfil perfil, Alumno alumno, Docente docente, Director director, Coordinador coordinador, Panolero panolero) {
        this.username = username;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.perfil = perfil;
        this.alumno = alumno;
        this.docente = docente;
        this.director = director;
        this.coordinador = coordinador;
        this.panolero = panolero;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    public Panolero getPanolero() {
        return panolero;
    }

    public void setPanolero(Panolero panolero) {
        this.panolero = panolero;
    }
}
