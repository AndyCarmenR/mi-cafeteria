package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity(name = "TICKET_VENTA")
public class TicketVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TICKET")
    private Integer idTicket;

    @Column(name = "FOLIO_TICKET")
    private String folioTicket;

    @Column(name = "FECHA_TICKET")
    private LocalDate fechaTicket;

    @OneToOne
    @JoinColumn(name ="USUARIO_INFO_ID")
    private UsuarioInfo usuarioInfo;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_PAGO")
    private CatTipoPago tipoPago;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL,orphanRemoval = true)
    private ArrayList<DetalleTicket> detalles=new ArrayList<>();

    @Column(name = "SUBTOTAL", precision = 10,scale = 2)
    private BigDecimal subtotal;

    @Column(name = "IMPUESTOS", precision = 10,scale = 2)
    private BigDecimal impuestos;

    @Column(name = "TOTAL", precision = 10,scale = 2)
    private BigDecimal total;

    @Column(name="OBSERVACIONES")
    private String observaciones;

    @Column(name = "ACTIVO")
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

    public UsuarioInfo getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(UsuarioInfo usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public CatTipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(CatTipoPago tipoPago) {
        this.tipoPago = tipoPago;
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

    public ArrayList<DetalleTicket> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleTicket> detalles) {
        this.detalles = detalles;
    }

    public void addDetalle(DetalleTicket detalleTicket){
        this.detalles.add(detalleTicket);
    }
    public void removeDetalle(DetalleTicket detalleTicket){
        detalleTicket.setTicketVenta(null);
        this.detalles.remove(detalleTicket);
    }
}
