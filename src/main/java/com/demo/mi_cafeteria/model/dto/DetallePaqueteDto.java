package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.DetallePaquete;

public class DetallePaqueteDto {

    private Integer idDetallePaquete;
    private PaqueteDto paquete;
    private ArticuloVentaDto articulo;
    private Integer cantidad;

    public Integer getIdDetallePaquete() {
        return idDetallePaquete;
    }

    public void setIdDetallePaquete(Integer idDetallePaquete) {
        this.idDetallePaquete = idDetallePaquete;
    }

    public PaqueteDto getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteDto paquete) {
        this.paquete = paquete;
    }

    public ArticuloVentaDto getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloVentaDto articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public static DetallePaqueteDto convertToDto(DetallePaquete detalle){
        DetallePaqueteDto dto=new DetallePaqueteDto();
        dto.setCantidad(detalle.getCantidad());
        dto.setPaquete(PaqueteDto.convertToDto(detalle.getPaquete()));
        dto.setArticulo(ArticuloVentaDto.convertToArticuloVentaDto(detalle.getArticulo()));
        dto.setIdDetallePaquete(detalle.getIdDetallePaquete());
        return dto;
    }
}
