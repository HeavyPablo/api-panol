package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "ESCUELA")
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private int id;

    @Column(name = "NOMBRE", length = 150, nullable = false)
    private String nombre;

    // Constructores
    public Escuela() {
    }

    public Escuela(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
