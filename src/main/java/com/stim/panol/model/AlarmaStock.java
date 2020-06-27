package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "ALARMA_STOCK")
public class AlarmaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SUBPRODUCTO_SA", nullable = false)
    private String subproductoAS;

    @Column(name = "ESCUELA_SA", nullable = false)
    private String escuelaSA;

    @Column(name = "ACTUALIZACION_SA", nullable = false)
    private String actualizacionSA;

    @Column(name = "STOCK", nullable = false)
    private int stock;

    public AlarmaStock() {
    }

    public AlarmaStock(String subproductoAS, String escuelaSA, String actualizacionSA, int stock) {
        this.subproductoAS = subproductoAS;
        this.escuelaSA = escuelaSA;
        this.actualizacionSA = actualizacionSA;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubproductoAS() {
        return subproductoAS;
    }

    public void setSubproductoAS(String subproductoAS) {
        this.subproductoAS = subproductoAS;
    }

    public String getEscuelaSA() {
        return escuelaSA;
    }

    public void setEscuelaSA(String escuelaSA) {
        this.escuelaSA = escuelaSA;
    }

    public String getActualizacionSA() {
        return actualizacionSA;
    }

    public void setActualizacionSA(String actualizacionSA) {
        this.actualizacionSA = actualizacionSA;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
