package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.CatExtras;

import java.math.BigDecimal;

public class ExtrasDto {
    private Integer idExtra;
    private String nombreExtra;
    private String descripcion;
    private BigDecimal precioExtra;

    public Integer getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(Integer idExtra) {
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

    public static ExtrasDto convertToDto(CatExtras extras){
        ExtrasDto dto=new ExtrasDto();
        dto.setDescripcion(extras.getDescripcion());
        dto.setIdExtra(extras.getIdExtra());
        dto.setNombreExtra(extras.getNombreExtra());
        dto.setPrecioExtra(extras.getPrecioExtra());

        return dto;
    }
}
