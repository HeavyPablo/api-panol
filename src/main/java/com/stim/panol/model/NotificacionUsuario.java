package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICACIONES_USUARIO")
public class NotificacionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOTIFICION", nullable = false)
    private String notificacion;

    @Column(name = "OPERACION", nullable = false)
    private String operacion;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "FECHA", nullable = false)
    private String fecha;

    @ManyToOne
    private Usuario usuario;

    public NotificacionUsuario() {
    }

    public NotificacionUsuario(String notificacion, String operacion, String estado, String fecha, Usuario usuario) {
        this.notificacion = notificacion;
        this.operacion = operacion;
        this.estado = estado;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
