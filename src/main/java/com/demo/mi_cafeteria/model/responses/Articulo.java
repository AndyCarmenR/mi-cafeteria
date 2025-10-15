package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.entity.CatArticulosVenta;

import java.math.BigDecimal;

public class Articulo {
    private Integer idArticuloVenta;
    private String nombreArticulo;
    private String descripcionArticulo;
    private BigDecimal precioArticulo;

    public Articulo(Integer idArticuloVenta, String nombreArticulo, String descripcionArticulo, BigDecimal precioArticulo) {
        this.idArticuloVenta = idArticuloVenta;
        this.nombreArticulo = nombreArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.precioArticulo = precioArticulo;
    }

    public Articulo() {
    }
    public Articulo(CatArticulosVenta articulosVenta) {
        this.idArticuloVenta=articulosVenta.getIdArticuloVenta();
        this.nombreArticulo=articulosVenta.getNombreArticulo();
        this.descripcionArticulo=articulosVenta.getDescripcionArticulo();
        this.precioArticulo=articulosVenta.getPrecioArticulo();
    }

    public Integer getIdArticuloVenta() {
        return idArticuloVenta;
    }

    public void setIdArticuloVenta(Integer idArticuloVenta) {
        this.idArticuloVenta = idArticuloVenta;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public BigDecimal getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(BigDecimal precioArticulo) {
        this.precioArticulo = precioArticulo;
    }
}
