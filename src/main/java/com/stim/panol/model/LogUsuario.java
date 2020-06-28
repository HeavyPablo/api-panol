package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "LOG_USUARIOS")
public class LogUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "LOG", length = 255, nullable = false)
    private String log;

    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;

    @Column(name = "ESCUELA_USUARIO", nullable = false)
    private String escuelaAfectado;

    @Column(name = "ID_RESPONSABLE", nullable = false)
    private int idResponsable;

    @Column(name = "PERFIL", nullable = false)
    private String perfil;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "FECHA_CREACION_USUARIO", nullable = false)
    private String fechaCreacionUsuario;

    @Column(name = "FECHA_CAMBIO_USUARIO", nullable = false)
    private String fechaCambioUsuario;

    // Constructores
    public LogUsuario() {
    }

    public LogUsuario(String log, int idUsuario, String escuelaAfectado, int idResponsable, String perfil, String estado, String fechaCreacionUsuario, String fechaCambioUsuario) {
        this.log = log;
        this.idUsuario = idUsuario;
        this.escuelaAfectado = escuelaAfectado;
        this.idResponsable = idResponsable;
        this.perfil = perfil;
        this.estado = estado;
        this.fechaCreacionUsuario = fechaCreacionUsuario;
        this.fechaCambioUsuario = fechaCambioUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEscuelaAfectado() {
        return escuelaAfectado;
    }

    public void setEscuelaAfectado(String escuelaAfectado) {
        this.escuelaAfectado = escuelaAfectado;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacionUsuario() {
        return fechaCreacionUsuario;
    }

    public void setFechaCreacionUsuario(String fechaCreacionUsuario) {
        this.fechaCreacionUsuario = fechaCreacionUsuario;
    }

    public String getFechaCambioUsuario() {
        return fechaCambioUsuario;
    }

    public void setFechaCambioUsuario(String fechaCambioUsuario) {
        this.fechaCambioUsuario = fechaCambioUsuario;
    }
}
