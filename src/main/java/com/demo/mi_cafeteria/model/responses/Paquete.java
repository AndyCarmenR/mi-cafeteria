package com.demo.mi_cafeteria.model.responses;

import java.math.BigDecimal;

public class Paquete {
    private Integer idPaquete;
    private String nombrePaquete;
    private String descripcion;
    private BigDecimal precio;

    public Paquete() {
    }
    public Paquete(com.demo.mi_cafeteria.model.entity.Paquete paquete) {
        this.idPaquete=paquete.getIdPaquete();
        this.nombrePaquete=paquete.getNombrePaquete();
        this.descripcion=paquete.getDescripcion();
        this.precio=paquete.getPrecio();
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
