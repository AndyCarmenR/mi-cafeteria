package com.demo.mi_cafeteria.model.dto;
import com.demo.mi_cafeteria.model.entity.CatExtras;
import com.demo.mi_cafeteria.model.entity.DetalleTicket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalleTicketDto {

    private Integer idDetalleTicket;
    private ArticuloVentaDto articuloVenta;
    private Integer idTicketVenta;
    private Integer cantidadArticulos;
    private BigDecimal totalVenta;
    private List<ExtrasDto> extras;
    private DescuentoArticuloDto descuento;
    private PaqueteDto paquete;

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

    public List<ExtrasDto> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtrasDto> extras) {
        this.extras = extras;
    }

    public DescuentoArticuloDto getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoArticuloDto descuento) {
        this.descuento = descuento;
    }

    public PaqueteDto getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteDto paquete) {
        this.paquete = paquete;
    }

    public static DetalleTicketDto convertToDetalleTicketDto(DetalleTicket detalleTicket){
        DetalleTicketDto detalleTicketDto=new DetalleTicketDto();
        detalleTicketDto.setIdDetalleTicket(detalleTicket.getIdDetalleTicket());
        detalleTicketDto.setArticuloVenta(ArticuloVentaDto.convertToArticuloVentaDto(detalleTicket.getArticuloVenta()));
        detalleTicketDto.setTicketVenta(detalleTicket.getTicketVenta().getIdTicket());
        detalleTicketDto.setCantidadArticulos(detalleTicket.getCantidadArticulos());
        detalleTicketDto.setTotalVenta(detalleTicket.getTotalVenta());
        List<ExtrasDto>extrasDtos =new ArrayList<>();
        for (CatExtras extras: detalleTicket.getExtras()){
            extrasDtos.add(ExtrasDto.convertToDto(extras));
        }
        detalleTicketDto.setExtras(extrasDtos);
        detalleTicketDto.setDescuento(DescuentoArticuloDto.convertToDto(detalleTicket.getDescuentoArticulo()));
        detalleTicketDto.setPaquete(PaqueteDto.convertToDto(detalleTicket.getPaquete()));
        return detalleTicketDto;
    }
}
