package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "IMAGEN_PRODUCTO")
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TIPO")
    private String tipo;

    @OneToOne(mappedBy = "imagenProducto")
    private Producto producto;

    public ImagenProducto() {
    }

    public ImagenProducto(byte[] imagen, String nombre, String tipo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
