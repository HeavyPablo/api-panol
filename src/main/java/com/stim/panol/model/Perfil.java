package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "PERFILES")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PERFIL", length = 150, nullable = false)
    private String perfil;

    // Constructores
    public Perfil() {
    }

    public Perfil(String perfil) {
        this.perfil = perfil;
    }

    // Getters & Setters
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
