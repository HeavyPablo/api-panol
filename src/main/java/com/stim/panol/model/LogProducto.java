package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "LOG_PRODUCTOS")
public class LogProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "LOG", length = 255, nullable = false)
    private String log;

    @Column(name = "ID_PRODUCTO", nullable = false)
    private int idProducto;

    @Column(name = "OPERACION", length = 150, nullable = false)
    private String operacion;

    @Column(name = "ESTADO", length = 150, nullable = false)
    private String estadoSolicitud;

    @Column(name = "ID_ESCUELA", nullable = false)
    private int idEscuela;

    @Column(name = "ID_PANOLERO_RESPONSABLE", nullable = false)
    private int idResponsable;

    @Column(name = "FECHA", nullable = false)
    private String fecha;

    // Constructores
    public LogProducto() {
    }

    public LogProducto(String log, int idProducto, String operacion, String estadoSolicitud, int idEscuela, int idResponsable, String fecha) {
        this.log = log;
        this.idProducto = idProducto;
        this.operacion = operacion;
        this.estadoSolicitud = estadoSolicitud;
        this.idEscuela = idEscuela;
        this.idResponsable = idResponsable;
        this.fecha = fecha;
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
