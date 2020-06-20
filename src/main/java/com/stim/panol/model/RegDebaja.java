package com.stim.panol.model;
import javax.persistence.*;

@Entity
@Table(name = "REG_DEBAJA")
public class RegDebaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ID_USUARIO_AFECTADO", nullable = false)
    private int idUsuarioRD;

    @Column(name = "NOM_USUARIO_RD", nullable = false)
    private String nomUsuarioRD;

    @Column(name = "PERFIL_RD", nullable = false)
    private String perfilRD;

    @Column(name = "ID_USUARIO_RESPONSABLE_RD", nullable = false)
    private int idResponsableRD;

    @Column(name = "NOM_RESPONSABLE_RD", nullable = false)
    private String nomResponsableRD;

    @Column(name = "PERFIL_RESPONSABLE_RD", nullable = false)
    private String perfilResponsableRD;

    @Column(name = "ESCUELA_RD", nullable = false)
    private String escuelaRD;

    @Column(name = "FECHA_CREACION_USUARIO_RD", nullable = false)
    private String fechaCreacionUsuarioRD;

    @Column(name = "FECHA_DEBAJA_USUARIO", nullable = false)
    private String fechaBloqueoUsuarioRD;

    @Column(name = "CARRERA_EST_DEBAJA")
    private String carreraBloqueado;

    @Column(name = "RAZON_RD")
    private String razon;


    public RegDebaja(){

    }

    public RegDebaja(int idUsuarioRD, String nomUsuarioRD, String perfilRD, int idResponsableRD, String nomResponsableRD, String perfilResponsableRD, String escuelaRD, String fechaCreacionUsuarioRD, String fechaBloqueoUsuarioRD, String carreraBloqueado, String razon) {
        this.idUsuarioRD = idUsuarioRD;
        this.nomUsuarioRD = nomUsuarioRD;
        this.perfilRD = perfilRD;
        this.idResponsableRD = idResponsableRD;
        this.nomResponsableRD = nomResponsableRD;
        this.perfilResponsableRD = perfilResponsableRD;
        this.escuelaRD = escuelaRD;
        this.fechaCreacionUsuarioRD = fechaCreacionUsuarioRD;
        this.fechaBloqueoUsuarioRD = fechaBloqueoUsuarioRD;
        this.carreraBloqueado = carreraBloqueado;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuarioRD() {
        return idUsuarioRD;
    }

    public void setIdUsuarioRD(int idUsuarioRD) {
        this.idUsuarioRD = idUsuarioRD;
    }

    public String getNomUsuarioRD() {
        return nomUsuarioRD;
    }

    public void setNomUsuarioRD(String nomUsuarioRD) {
        this.nomUsuarioRD = nomUsuarioRD;
    }

    public String getPerfilRD() {
        return perfilRD;
    }

    public void setPerfilRD(String perfilRD) {
        this.perfilRD = perfilRD;
    }

    public int getIdResponsableRD() {
        return idResponsableRD;
    }

    public void setIdResponsableRD(int idResponsableRD) {
        this.idResponsableRD = idResponsableRD;
    }

    public String getNomResponsableRD() {
        return nomResponsableRD;
    }

    public void setNomResponsableRD(String nomResponsableRD) {
        this.nomResponsableRD = nomResponsableRD;
    }

    public String getPerfilResponsableRD() {
        return perfilResponsableRD;
    }

    public void setPerfilResponsableRD(String perfilResponsableRD) {
        this.perfilResponsableRD = perfilResponsableRD;
    }

    public String getEscuelaRD() {
        return escuelaRD;
    }

    public void setEscuelaRD(String escuelaRD) {
        this.escuelaRD = escuelaRD;
    }

    public String getFechaCreacionUsuarioRD() {
        return fechaCreacionUsuarioRD;
    }

    public void setFechaCreacionUsuarioRD(String fechaCreacionUsuarioRD) {
        this.fechaCreacionUsuarioRD = fechaCreacionUsuarioRD;
    }

    public String getFechaBloqueoUsuarioRD() {
        return fechaBloqueoUsuarioRD;
    }

    public void setFechaBloqueoUsuarioRD(String fechaBloqueoUsuarioRD) {
        this.fechaBloqueoUsuarioRD = fechaBloqueoUsuarioRD;
    }

    public String getCarreraBloqueado() {
        return carreraBloqueado;
    }

    public void setCarreraBloqueado(String carreraBloqueado) {
        this.carreraBloqueado = carreraBloqueado;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}
