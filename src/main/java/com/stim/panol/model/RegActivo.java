package com.stim.panol.model;
import javax.persistence.*;

@Entity
@Table(name = "REG_ACTIVO")
public class RegActivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ID_USUARIO_ACTIVADO", nullable = false)
    private int idUsuarioRA;

    @Column(name = "NOM_USUARIO_RA", nullable = false)
    private String nomUsuarioRA;

    @Column(name = "PERFIL_RA", nullable = false)
    private String perfilRA;

    @Column(name = "ESTADO_RA", nullable = false)
    private String estadoRA;

    @Column(name = "ID_USUARIO_RESPONSABLE_RA", nullable = false)
    private int idResponsableRA;

    @Column(name = "NOM_RESPONSABLE_RA", nullable = false)
    private String nomResponsableRA;

    @Column(name = "PERFIL_RESPONSABLE_RA", nullable = false)
    private String perfilResponsableRA;

    @Column(name = "ESCUELA_RA", nullable = false)
    private String escuelaRA;

    @Column(name = "FECHA_CREACION_USUARIO_RA", nullable = false)
    private String fechaCreacionUsuarioRA;

    @Column(name = "FECHA_ACTIVACION_USUARIO", nullable = false)
    private String fechaActivacionUsuarioRA;

    @Column(name = "CARRERA_EST_ACTIVADA")
    private String carreraActivada;

    @Column(name = "JUSTIFICACION")
    private String justificacion;

    public RegActivo(){}

    public RegActivo(int idUsuarioRA, String nomUsuarioRA, String perfilRA, String estadoRA, int idResponsableRA, String nomResponsableRA, String perfilResponsableRA, String escuelaRA, String fechaCreacionUsuarioRA, String fechaActivacionUsuarioRA, String carreraActivada, String justificacion) {
        this.idUsuarioRA = idUsuarioRA;
        this.nomUsuarioRA = nomUsuarioRA;
        this.perfilRA = perfilRA;
        this.estadoRA = estadoRA;
        this.idResponsableRA = idResponsableRA;
        this.nomResponsableRA = nomResponsableRA;
        this.perfilResponsableRA = perfilResponsableRA;
        this.escuelaRA = escuelaRA;
        this.fechaCreacionUsuarioRA = fechaCreacionUsuarioRA;
        this.fechaActivacionUsuarioRA = fechaActivacionUsuarioRA;
        this.carreraActivada = carreraActivada;
        this.justificacion = justificacion;
    }

    public String getEstadoRA() {
        return estadoRA;
    }

    public void setEstadoRA(String estadoRA) {
        this.estadoRA = estadoRA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuarioRA() {
        return idUsuarioRA;
    }

    public void setIdUsuarioRA(int idUsuarioRA) {
        this.idUsuarioRA = idUsuarioRA;
    }

    public String getNomUsuarioRA() {
        return nomUsuarioRA;
    }

    public void setNomUsuarioRA(String nomUsuarioRA) {
        this.nomUsuarioRA = nomUsuarioRA;
    }

    public String getPerfilRA() {
        return perfilRA;
    }

    public void setPerfilRA(String perfilRA) {
        this.perfilRA = perfilRA;
    }

    public int getIdResponsableRA() {
        return idResponsableRA;
    }

    public void setIdResponsableRA(int idResponsableRA) {
        this.idResponsableRA = idResponsableRA;
    }

    public String getNomResponsableRA() {
        return nomResponsableRA;
    }

    public void setNomResponsableRA(String nomResponsableRA) {
        this.nomResponsableRA = nomResponsableRA;
    }

    public String getPerfilResponsableRA() {
        return perfilResponsableRA;
    }

    public void setPerfilResponsableRA(String perfilResponsableRA) {
        this.perfilResponsableRA = perfilResponsableRA;
    }

    public String getEscuelaRA() {
        return escuelaRA;
    }

    public void setEscuelaRA(String escuelaRA) {
        this.escuelaRA = escuelaRA;
    }

    public String getFechaCreacionUsuarioRA() {
        return fechaCreacionUsuarioRA;
    }

    public void setFechaCreacionUsuarioRA(String fechaCreacionUsuarioRA) {
        this.fechaCreacionUsuarioRA = fechaCreacionUsuarioRA;
    }

    public String getFechaActivacionUsuarioRA() {
        return fechaActivacionUsuarioRA;
    }

    public void setFechaActivacionUsuarioRA(String fechaActivacionUsuarioRA) {
        this.fechaActivacionUsuarioRA = fechaActivacionUsuarioRA;
    }

    public String getCarreraActivada() {
        return carreraActivada;
    }

    public void setCarreraActivada(String carreraActivada) {
        this.carreraActivada = carreraActivada;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}
