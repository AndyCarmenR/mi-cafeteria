package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.DetalleTicket;
import com.demo.mi_cafeteria.model.entity.TicketVenta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketDto {

    private Integer idTicket;
    private String folioTicket;
    private LocalDate fechaTicket;
    private UsuarioDto usuarioInfo;
    private TipoPagoDto tipoPago;
    private ArrayList<DetalleTicketDto> detalles=new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String observaciones;
    private Boolean activo;

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getFolioTicket() {
        return folioTicket;
    }

    public void setFolioTicket(String folioTicket) {
        this.folioTicket = folioTicket;
    }

    public LocalDate getFechaTicket() {
        return fechaTicket;
    }

    public void setFechaTicket(LocalDate fechaTicket) {
        this.fechaTicket = fechaTicket;
    }

    public UsuarioDto getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(UsuarioDto usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public TipoPagoDto getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPagoDto tipoPago) {
        this.tipoPago = tipoPago;
    }

    public ArrayList<DetalleTicketDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleTicketDto> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public static TicketDto convertToTicketDto(TicketVenta ticket){
        TicketDto ticketDto=new TicketDto();
        ticketDto.setIdTicket(ticket.getIdTicket());
        ticketDto.setFolioTicket(ticket.getFolioTicket());
        ticketDto.setFechaTicket(ticket.getFechaTicket());
        UsuarioDto usuarioDto=new UsuarioDto(ticket.getUsuarioInfo());
        ticketDto.setUsuarioInfo(usuarioDto);
        ticketDto.setTipoPago(TipoPagoDto.convertToDto(ticket.getTipoPago()));

        ArrayList<DetalleTicketDto>detalleDtoList=new ArrayList<>();
        for (DetalleTicket detalleTicket : ticket.getDetalles()){
            DetalleTicketDto converted=DetalleTicketDto.convertToDetalleTicketDto(detalleTicket);
            detalleDtoList.add(converted);
        }
        ticketDto.setDetalles(detalleDtoList);
        ticketDto.setSubtotal(ticket.getSubtotal());
        ticketDto.setImpuestos(ticket.getImpuestos());
        ticketDto.setTotal(ticket.getTotal());
        ticketDto.setObservaciones(ticket.getObservaciones());
        ticketDto.setActivo(ticket.getActivo());
        
        return ticketDto;
    }
}
