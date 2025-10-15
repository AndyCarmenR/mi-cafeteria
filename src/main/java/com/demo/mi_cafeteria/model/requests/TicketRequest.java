package com.demo.mi_cafeteria.model.requests;

import com.demo.mi_cafeteria.model.responses.Detalle;
import com.demo.mi_cafeteria.model.responses.Paquete;

import java.util.List;

public class TicketRequest {
    private  Integer idTicket;
    private List<Detalle> detalles;
    private Integer idTipoPago;
    private String observaciones;

    public TicketRequest(Integer idTicket, List<Detalle> detalles, Integer idTipoPago, String observaciones) {
        this.idTicket = idTicket;
        this.detalles = detalles;
        this.idTipoPago = idTipoPago;
        this.observaciones = observaciones;
    }

    public TicketRequest() {
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
