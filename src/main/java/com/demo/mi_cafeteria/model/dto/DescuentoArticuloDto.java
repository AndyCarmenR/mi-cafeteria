package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.DescuentoArticulo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class DescuentoArticuloDto {

    private Integer idDescuento;

    private String nombreDescuento;

    private String descripcion;

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

    public static DescuentoArticuloDto convertToDto(DescuentoArticulo descuentoArticulo){
        DescuentoArticuloDto dto=new DescuentoArticuloDto();
        dto.setDescripcion(descuentoArticulo.getDescripcion());
        dto.setIdDescuento(descuentoArticulo.getIdDescuento());
        dto.setDescuentoPorcentaje(descuentoArticulo.getDescuentoPorcentaje());
        dto.setNombreDescuento(descuentoArticulo.getNombreDescuento());

        return dto;
    }
}
