package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "DESCUENTO_ARTICULO")
public class DescuentoArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESCUENTO")
    private Integer idDescuento;

    @Column(name = "NOMBRE_DESCUENTO")
    private String nombreDescuento;

    @Column(name = "DESCRIPCION_DESCUENTO")
    private String descripcion;

    @Column(name = "DESCUENTO_PORCENTAJE",precision = 10,scale = 2)
    private BigDecimal descuentoPorcentaje;

    public Integer getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getNombreDescuento() {
        return nombreDescuento;
    }

    public void setNombreDescuento(String nombreDescuento) {
        this.nombreDescuento = nombreDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(BigDecimal descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }
}
