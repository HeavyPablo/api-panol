package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "REG_BLOQUEO")
public class RegBloqueo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ID_USUARIO_AFECTADO", nullable = false)
    private int idUsuarioRB;

    @Column(name = "NOM_USUARIO_RB", nullable = false)
    private String nomUsuarioRB;

    @Column(name = "PERFIL_RB", nullable = false)
    private String perfilRB;

    @Column(name = "ID_USUARIO_RESPONSABLE_RB", nullable = false)
    private int idResponsableRB;

    @Column(name = "NOM_RESPONSABLE_RB", nullable = false)
    private String nomResponsableRB;

    @Column(name = "PERFIL_RESPONSABLE_RB", nullable = false)
    private String perfilResponsableRB;

    @Column(name = "ESCUELA_RB", nullable = false)
    private String escuelaRB;

    @Column(name = "FECHA_CREACION_USUARIO_RB", nullable = false)
    private String fechaCreacionUsuarioRB;

    @Column(name = "FECHA_BLOQUEO_USUARIO", nullable = false)
    private String fechaBloqueoUsuarioRB;

    @Column(name = "FECHA_DESBLOQUEO_USUARIO")
    private String fechaDesbloqueoUsuarioRB;

    @Column(name = "CARRERA_EST_BLOQUEADO")
    private String carreraBloqueado;

    @Column(name = "RAZON")
    private String razon;

    //contructores
    public RegBloqueo(){
    }

    public RegBloqueo(int idUsuarioRB, String nomUsuarioRB, String perfilRB, int idResponsableRB, String nomResponsableRB, String perfilResponsableRB, String escuelaRB, String fechaCreacionUsuarioRB, String fechaBloqueoUsuarioRB, String fechaDesbloqueoUsuarioRB,String carreraBloqueado, String razon) {
        this.idUsuarioRB = idUsuarioRB;
        this.nomUsuarioRB = nomUsuarioRB;
        this.perfilRB = perfilRB;
        this.idResponsableRB = idResponsableRB;
        this.nomResponsableRB = nomResponsableRB;
        this.perfilResponsableRB = perfilResponsableRB;
        this.escuelaRB = escuelaRB;
        this.fechaCreacionUsuarioRB = fechaCreacionUsuarioRB;
        this.fechaBloqueoUsuarioRB = fechaBloqueoUsuarioRB;
        this.fechaDesbloqueoUsuarioRB = fechaDesbloqueoUsuarioRB;
        this.carreraBloqueado = carreraBloqueado;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuarioRB() {
        return idUsuarioRB;
    }

    public void setIdUsuarioRB(int idUsuarioRB) {
        this.idUsuarioRB = idUsuarioRB;
    }

    public String getNomUsuarioRB() {
        return nomUsuarioRB;
    }

    public void setNomUsuarioRB(String nomUsuarioRB) {
        this.nomUsuarioRB = nomUsuarioRB;
    }

    public String getPerfilRB() {
        return perfilRB;
    }

    public void setPerfilRB(String perfilRB) {
        this.perfilRB = perfilRB;
    }

    public int getIdResponsableRB() {
        return idResponsableRB;
    }

    public void setIdResponsableRB(int idResponsableRB) {
        this.idResponsableRB = idResponsableRB;
    }

    public String getNomResponsableRB() {
        return nomResponsableRB;
    }

    public void setNomResponsableRB(String nomResponsableRB) {
        this.nomResponsableRB = nomResponsableRB;
    }

    public String getPerfilResponsableRB() {
        return perfilResponsableRB;
    }

    public void setPerfilResponsableRB(String perfilResponsableRB) {
        this.perfilResponsableRB = perfilResponsableRB;
    }

    public String getEscuelaRB() {
        return escuelaRB;
    }

    public void setEscuelaRB(String escuelaRB) {
        this.escuelaRB = escuelaRB;
    }

    public String getFechaCreacionUsuarioRB() {
        return fechaCreacionUsuarioRB;
    }

    public void setFechaCreacionUsuarioRB(String fechaCreacionUsuarioRB) {
        this.fechaCreacionUsuarioRB = fechaCreacionUsuarioRB;
    }

    public String getFechaBloqueoUsuarioRB() {
        return fechaBloqueoUsuarioRB;
    }

    public void setFechaBloqueoUsuarioRB(String fechaBloqueoUsuarioRB) {
        this.fechaBloqueoUsuarioRB = fechaBloqueoUsuarioRB;
    }

    public String getFechaDesbloqueoUsuarioRB() {
        return fechaDesbloqueoUsuarioRB;
    }

    public void setFechaDesbloqueoUsuarioRB(String fechaDesbloqueoUsuarioRB) {
        this.fechaDesbloqueoUsuarioRB = fechaDesbloqueoUsuarioRB;
    }

    public String getRazon() {
        return razon;
    }

    public String getCarreraBloqueado() {
        return carreraBloqueado;
    }

    public void setCarreraBloqueado(String carreraBloqueado) {
        this.carreraBloqueado = carreraBloqueado;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}
