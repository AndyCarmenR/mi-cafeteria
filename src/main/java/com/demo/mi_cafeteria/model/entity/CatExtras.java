package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "CAT_EXTRAS")
public class CatExtras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXTRA")
    private Integer idExtra;

    @Column(name = "NOMBRE_EXTRA")
    private String nombreExtra;

    @Column(name = "DESCRIPCION_EXTRA")
    private String descripcion;

    @Column(name = "PRECIO_EXTRA", precision = 10, scale = 2)
    private BigDecimal precioExtra;

    public Integer getIdExtra() {
        return idExtra;
    }

    public void setIdTipoArticulo(Integer idExtra) {
        this.idExtra = idExtra;
    }

    public String getNombreExtra() {
        return nombreExtra;
    }

    public void setNombreExtra(String nombreExtra) {
        this.nombreExtra = nombreExtra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioExtra() {
        return precioExtra;
    }

    public void setPrecioExtra(BigDecimal precioExtra) {
        this.precioExtra = precioExtra;
    }
}
