package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.CatArticulosVenta;
import com.demo.mi_cafeteria.model.entity.CatTipoArticulo;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class ArticuloVentaDto {

    private Integer idArticuloVenta;
    private String nombreArticulo;
    private String descripcionArticulo;
    private BigDecimal precioArticulo;
    private TipoArticuloDto tipoArticulo;

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

    public TipoArticuloDto getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticuloDto tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public static ArticuloVentaDto convertToArticuloVentaDto(CatArticulosVenta articulosVenta){
        ArticuloVentaDto articuloVentaDto=new ArticuloVentaDto();
        articuloVentaDto.setIdArticuloVenta(articulosVenta.getIdArticuloVenta());
        articuloVentaDto.setTipoArticulo(TipoArticuloDto.convertToDto(articulosVenta.getTipoArticulo()));
        articuloVentaDto.setNombreArticulo(articulosVenta.getNombreArticulo());
        articuloVentaDto.setPrecioArticulo(articulosVenta.getPrecioArticulo());
        articuloVentaDto.setDescripcionArticulo(articulosVenta.getDescripcionArticulo());
        return articuloVentaDto;

    }
}
