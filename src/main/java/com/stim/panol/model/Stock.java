package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad = 0;

    @OneToOne
    private Subcategoria subcategoria;

    // Constructores
    public Stock() {
    }

    public Stock(int cantidad, Subcategoria subcategoria) {
        this.cantidad = cantidad;
        this.subcategoria = subcategoria;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
}
