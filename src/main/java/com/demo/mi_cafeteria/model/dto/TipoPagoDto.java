package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.CatTipoPago;

public class TipoPagoDto {
    private Integer idTipoPago;
    private String tipoPago;
    private String descripcion;

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static TipoPagoDto convertToDto(CatTipoPago tipoPago){
        TipoPagoDto tipoPagoDto=new TipoPagoDto();
        tipoPagoDto.setIdTipoPago(tipoPago.getIdTipoPago());
        tipoPagoDto.setTipoPago(tipoPago.getTipoPago());
        tipoPagoDto.setDescripcion(tipoPago.getDescripcionPago());
        return tipoPagoDto;
    }
}
