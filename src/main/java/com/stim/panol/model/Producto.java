package com.stim.panol.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE", length = 200, nullable = false)
    private String nombre;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Column(name = "CANTIDAD", nullable = false)
    private String cantidad;

    @Column(name = "FECHA_CREACION", nullable = false)
    private String fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private String fechaActualizacion;

    // Relaciones
    @ManyToOne
    private Subcategoria subcategoria;

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Solicitud> solicitudes;

    // Constructores
    public Producto() {
    }

    public Producto(String nombre, String estado, String descripcion, String cantidad, String fechaCreacion, String fechaActualizacion, Subcategoria subcategoria) {
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.subcategoria = subcategoria;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Set<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(Set<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
