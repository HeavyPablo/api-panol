package com.stim.panol.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SOLICITUDES")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "TIPO_SOLICITUD", length = 150, nullable = false)
    private String tipoSolicitud;

    @Column(name = "DIAS_SOLICITADOS")
    private String diasSolicitados;

    @Column(name = "ESTADO", length = 150, nullable = false)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private String fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private String fechaActualizacion;

    // Relaciones
    @JoinColumn(name = "USUARIO_SOLICITANTE")
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(name = "PANOLERO_RESPONSABLE")
    @ManyToOne
    private Panolero panolero;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
    private Set<Producto> productos;

    // Constructores
    public Solicitud() {
    }

    public Solicitud(String comentario, String tipoSolicitud, String estado, String fechaCreacion, String fechaActualizacion, Usuario usuario, Panolero panolero, Set<Producto> productos) {
        this.comentario = comentario;
        this.tipoSolicitud = tipoSolicitud;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
        this.panolero = panolero;
        this.productos = productos;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiasSolicitados() {
        return diasSolicitados;
    }

    public void setDiasSolicitados(String diasSolicitados) {
        this.diasSolicitados = diasSolicitados;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Panolero getPanolero() {
        return panolero;
    }

    public void setPanolero(Panolero panolero) {
        this.panolero = panolero;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
