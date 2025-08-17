package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "DETALLE_TICKET")
public class DetalleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_TICKET")
    private Integer idDetalleTicket;

    @OneToOne
    @JoinColumn(name = "ID_ARTICULO_VENTA")
    private CatArticulosVenta articuloVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TICKET",nullable = false)
    private TicketVenta ticketVenta;

    @Column(name = "CANTIDAD_ARTICULOS")
    private Integer cantidadArticulos;

    @Column(name = "TOTAL_VENTA",precision = 10, scale = 2)
    private BigDecimal totalVenta;

    public Integer getIdDetalleTicket() {
        return idDetalleTicket;
    }

    public void setIdDetalleTicket(Integer idDetalleTicket) {
        this.idDetalleTicket = idDetalleTicket;
    }

    public CatArticulosVenta getArticuloVenta() {
        return articuloVenta;
    }

    public void setArticuloVenta(CatArticulosVenta articuloVenta) {
        this.articuloVenta = articuloVenta;
    }

    public TicketVenta getTicketVenta() {
        return ticketVenta;
    }

    public void setTicketVenta(TicketVenta ticketVenta) {
        this.ticketVenta = ticketVenta;
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
}
