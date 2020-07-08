package com.stim.panol.model;

import javax.persistence.*;

@Entity
@Table(name = "ALARMA_STOCK")
public class AlarmaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SUBPRODUCTO_AS", nullable = false)
    private String subproductoAS;

    @Column(name = "ESCUELA_AS", nullable = false)
    private String escuelaSA;

    @Column(name = "ACTUALIZACION_AS", nullable = false)
    private String actualizacionSA;

    @Column(name = "STOCK", nullable = false)
    private int stock;

    @Column(name = "ID_ESCUELA_AS", nullable = false)
    private int idEscuelaSA;

    @Column(name = "ID_PRODUCTO_AS", nullable = false)
    private int idProductoSA;

    @Column(name = "STOCK_TOTAL", nullable = false)
    private int stockTotal;

    public AlarmaStock() {
    }

    public AlarmaStock(String subproductoAS, String escuelaSA, String actualizacionSA, int stock, int idEscuelaSA, int idProductoSA, int stockTotal) {
        this.subproductoAS = subproductoAS;
        this.escuelaSA = escuelaSA;
        this.actualizacionSA = actualizacionSA;
        this.stock = stock;
        this.idEscuelaSA = idEscuelaSA;
        this.idProductoSA = idProductoSA;
        this.stockTotal = stockTotal;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public int getIdEscuelaSA() {
        return idEscuelaSA;
    }

    public void setIdEscuelaSA(int idEscuelaSA) {
        this.idEscuelaSA = idEscuelaSA;
    }

    public int getIdProductoSA() {
        return idProductoSA;
    }

    public void setIdProductoSA(int idProductoSA) {
        this.idProductoSA = idProductoSA;
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
