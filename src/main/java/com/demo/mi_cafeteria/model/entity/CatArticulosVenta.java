package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "CAT_ARTICULOS_VENTA")
public class CatArticulosVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARTICULO_VENTA")
    private Integer idArticuloVenta;

    @Column(name = "NOMBRE_ARTICULO")
    private String nombreArticulo;

    @Column(name = "DESCRIPCION_ARTICULO")
    private String descripcionArticulo;

    @Column(name = "PRECIO_ARTICULO",precision = 10,scale = 2)
    private BigDecimal precioArticulo;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_ARTICULO")
    private CatTipoArticulo tipoArticulo;

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

    public CatTipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(CatTipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }


}
