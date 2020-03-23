package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "CARERRA")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private int id;

    @Column(name = "NOMBRE", length = 150, nullable = false)
    private String nombre;

    // Relaciones
    @ManyToOne
    private Escuela escuela;

    // Constructores
    public Carrera() {
    }

    public Carrera(int id, String nombre) {
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

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
}
