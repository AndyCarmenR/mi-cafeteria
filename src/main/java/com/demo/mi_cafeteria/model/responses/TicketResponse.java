package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.dto.TipoPagoDto;
import com.demo.mi_cafeteria.model.dto.UsuarioDto;
import com.demo.mi_cafeteria.model.entity.DetalleTicket;
import com.demo.mi_cafeteria.model.entity.TicketVenta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketResponse {
    private Integer idTicket;
    private String folioTicket;
    private LocalDate fechaTicket;
    private UsuarioDto usuarioInfo;
    private TipoPagoDto tipoPago;
    private ArrayList<Detalle> detalles;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String observaciones;
    private Boolean activo;


    public TicketResponse(TicketVenta ticketVenta) {
        this.idTicket=ticketVenta.getIdTicket();
        this.folioTicket=ticketVenta.getFolioTicket();
        this.usuarioInfo=new UsuarioDto(ticketVenta.getUsuarioInfo());
        this.tipoPago=(ticketVenta.getTipoPago()!=null)?(TipoPagoDto.convertToDto(ticketVenta.getTipoPago())):(new TipoPagoDto());
        this.detalles=new ArrayList<>();
        for (DetalleTicket detalleTicket:ticketVenta.getDetalles()){
            Detalle detalle=new Detalle(detalleTicket);
            detalles.add(detalle);
        }
        this.subtotal=ticketVenta.getSubtotal();
        this.impuestos=ticketVenta.getImpuestos();
        this.total=ticketVenta.getTotal();
        this.observaciones=ticketVenta.getObservaciones();
        this.activo=ticketVenta.getActivo();
    }
    public TicketResponse() {
        detalles=new ArrayList<>();
    }

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

    public ArrayList<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<Detalle> detalles) {
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
}
