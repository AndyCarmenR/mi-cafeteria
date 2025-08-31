package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "DETALLE_TICKET")
public class DetalleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_TICKET")
    private Integer idDetalleTicket;

    @ManyToOne
    @JoinColumn(name = "ID_ARTICULO_VENTA")
    private CatArticulosVenta articuloVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TICKET",nullable = false)
    private TicketVenta ticketVenta;

    @Column(name = "CANTIDAD_ARTICULOS")
    private Integer cantidadArticulos;

    @Column(name = "TOTAL_VENTA",precision = 10, scale = 2)
    private BigDecimal totalVenta;

    @ManyToMany
    @JoinTable(
            name = "DETALLE_TICKET_EXTRAS", // tabla intermedia
            joinColumns = @JoinColumn(name = "ID_DETALLE_TICKET"),
            inverseJoinColumns = @JoinColumn(name = "ID_EXTRA")
    )
    private List<CatExtras> extras=new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "ID_DESCUENTO")
    private DescuentoArticulo descuentoArticulo;

    @ManyToOne
    @JoinColumn(name = "ID_PAQUETE")
    private Paquete paquete;


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

    public List<CatExtras> getExtras() {
        return extras;
    }

    public void setExtras(List<CatExtras> extras) {
        this.extras = extras;
    }

    public DescuentoArticulo getDescuentoArticulo() {
        return descuentoArticulo;
    }

    public void setDescuentoArticulo(DescuentoArticulo descuentoArticulo) {
        this.descuentoArticulo = descuentoArticulo;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
