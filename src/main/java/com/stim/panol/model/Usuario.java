package com.stim.panol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERNAME", length = 150, nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne
    private Perfil perfil;

    // Constructores
    public Usuario() {
    }

    public Usuario(String username, String password, Perfil perfil) {
        this.username = username;
        this.password = password;
        this.perfil = perfil;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
