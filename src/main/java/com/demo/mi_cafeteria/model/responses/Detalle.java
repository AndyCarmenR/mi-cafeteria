package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.dto.DescuentoArticuloDto;
import com.demo.mi_cafeteria.model.dto.ExtrasDto;
import com.demo.mi_cafeteria.model.entity.CatArticulosVenta;
import com.demo.mi_cafeteria.model.entity.CatExtras;
import com.demo.mi_cafeteria.model.entity.DetalleTicket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Detalle {
    private Integer idDetalleTicket;
    private Articulo articuloVenta;
    private Integer idTicketVenta;
    private Integer cantidadArticulos;
    private BigDecimal totalVenta;
    private List<ExtrasDto> extras;
    private DescuentoArticuloDto descuento;
    private Paquete paquete;

    public Detalle() {
        this.extras=new ArrayList<>();
    }
    public Detalle(DetalleTicket detalleTicket){
        this.idDetalleTicket=detalleTicket.getIdDetalleTicket();
        this.articuloVenta= new Articulo(detalleTicket.getArticuloVenta());
        this.idTicketVenta=detalleTicket.getTicketVenta().getIdTicket();
        this.cantidadArticulos=detalleTicket.getCantidadArticulos();
        this.totalVenta=detalleTicket.getTotalVenta();
        List<ExtrasDto>extrasDtoList=new ArrayList<>();
        for (CatExtras extras:detalleTicket.getExtras()){
            ExtrasDto extrasDto=ExtrasDto.convertToDto(extras);
            extrasDtoList.add(extrasDto);
        }
        this.extras=extrasDtoList;
        this.totalVenta=detalleTicket.getTotalVenta();
        this.descuento=new DescuentoArticuloDto();
        if (detalleTicket.getDescuentoArticulo() != null) {
            descuento=DescuentoArticuloDto.convertToDto(detalleTicket.getDescuentoArticulo());
        }
        if (detalleTicket.getPaquete() != null) {
            this.paquete=new Paquete(detalleTicket.getPaquete());
        }

    }

    public Integer getIdDetalleTicket() {
        return idDetalleTicket;
    }

    public void setIdDetalleTicket(Integer idDetalleTicket) {
        this.idDetalleTicket = idDetalleTicket;
    }

    public Articulo getArticuloVenta() {
        return articuloVenta;
    }

    public void setArticuloVenta(Articulo articuloVenta) {
        this.articuloVenta = articuloVenta;
    }

    public Integer getIdTicketVenta() {
        return idTicketVenta;
    }

    public void setIdTicketVenta(Integer idTicketVenta) {
        this.idTicketVenta = idTicketVenta;
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

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public  List<Detalle> toListDetalle(List<DetalleTicket> dettalleTicketList){
        List<Detalle> detalleListResponse=new ArrayList<>();
        for(DetalleTicket detalleTicket:dettalleTicketList){
            CatArticulosVenta catArticulosVenta=detalleTicket.getArticuloVenta();
            Articulo articulo=new Articulo();
            if (catArticulosVenta != null) {
                articulo=new Articulo(catArticulosVenta);
            }


        }
        return detalleListResponse;
    }
}
