package com.demo.mi_cafeteria.model.dto;
import com.demo.mi_cafeteria.model.entity.DetalleTicket;

import java.math.BigDecimal;

public class DetalleTicketDto {

    private Integer idDetalleTicket;
    private ArticuloVentaDto articuloVenta;
    private Integer idTicketVenta;
    private Integer cantidadArticulos;
    private BigDecimal totalVenta;

    public Integer getIdDetalleTicket() {
        return idDetalleTicket;
    }

    public void setIdDetalleTicket(Integer idDetalleTicket) {
        this.idDetalleTicket = idDetalleTicket;
    }

    public ArticuloVentaDto getArticuloVenta() {
        return articuloVenta;
    }

    public void setArticuloVenta(ArticuloVentaDto articuloVenta) {
        this.articuloVenta = articuloVenta;
    }

    public Integer getTicketVenta() {
        return idTicketVenta;
    }

    public void setTicketVenta(Integer ticketVenta) {
        this.idTicketVenta = ticketVenta;
    }

    public Integer getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(Integer cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }
    public static DetalleTicketDto convertToDetalleTicketDto(DetalleTicket detalleTicket){
        DetalleTicketDto detalleTicketDto=new DetalleTicketDto();
        detalleTicketDto.setIdDetalleTicket(detalleTicket.getIdDetalleTicket());
        detalleTicketDto.setArticuloVenta(ArticuloVentaDto.convertToArticuloVentaDto(detalleTicket.getArticuloVenta()));
        detalleTicketDto.setTicketVenta(detalleTicket.getTicketVenta().getIdTicket());
        detalleTicketDto.setCantidadArticulos(detalleTicket.getCantidadArticulos());
        detalleTicketDto.setTotalVenta(detalleTicket.getTotalVenta());

        return detalleTicketDto;
    }
}
