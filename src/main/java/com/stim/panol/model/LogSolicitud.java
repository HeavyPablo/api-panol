package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "LOG_SOLICITUDES")
public class LogSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "LOG", length = 255, nullable = false)
    private String log;

    @Column(name = "ID_SOLICITUD", nullable = false)
    private int idSolicitud;

    @Column(name = "ESTADO_SOLICITUD", length = 150, nullable = false)
    private String estadoSolicitud;

    @Column(name = "ID_USUARIO_SOLICITANTE", nullable = false)
    private int idUsuario;

    @Column(name = "TIPO_SOLICITUD", nullable = false)
    private String tipoSolicitud;

    @Column(name = "ID_ESCUELA_SOLICITANTE", nullable = false)
    private int idEscuelaSolicitante;

    @Column(name = "ID_USUARIO_RESPONSABLE")
    private int idResponsable;

    @Column(name = "FECHA_CAMBIO_SOLICITUD", nullable = false)
    private String fechaCambioSolicitud;

    @Column(name = "FECHA_CREACION_SOLICITUD", nullable = false)
    private String fechaCreacionSolicitud;

    // Constructores
    public LogSolicitud() {
    }

    public LogSolicitud(String log, int idSolicitud, String estadoSolicitud, int idUsuario, String tipoSolicitud, int idEscuelaSolicitante, int idResponsable, String fechaCambioSolicitud, String fechaCreacionSolicitud) {
        this.log = log;
        this.idSolicitud = idSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.idUsuario = idUsuario;
        this.tipoSolicitud = tipoSolicitud;
        this.idEscuelaSolicitante = idEscuelaSolicitante;
        this.idResponsable = idResponsable;
        this.fechaCambioSolicitud = fechaCambioSolicitud;
        this.fechaCreacionSolicitud = fechaCreacionSolicitud;
    }

    // Getters & Setters

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

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public int getIdEscuelaSolicitante() {
        return idEscuelaSolicitante;
    }

    public void setIdEscuelaSolicitante(int idEscuelaSolicitante) {
        this.idEscuelaSolicitante = idEscuelaSolicitante;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getFechaCambioSolicitud() {
        return fechaCambioSolicitud;
    }

    public void setFechaCambioSolicitud(String fechaCambioSolicitud) {
        this.fechaCambioSolicitud = fechaCambioSolicitud;
    }

    public String getFechaCreacionSolicitud() {
        return fechaCreacionSolicitud;
    }

    public void setFechaCreacionSolicitud(String fechaCreacionSolicitud) {
        this.fechaCreacionSolicitud = fechaCreacionSolicitud;
    }
}
