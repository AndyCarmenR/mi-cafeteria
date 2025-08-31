package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_PAQUETE")
public class DetallePaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_PAQUETE")
    private Integer idDetallePaquete;

    @ManyToOne
    @JoinColumn(name = "ID_PAQUETE")
    private Paquete paquete;

    @ManyToOne
    @JoinColumn(name = "ID_ARTICULO")
    private CatArticulosVenta articulo;

    private Integer cantidad;

    public Integer getIdDetallePaquete() {
        return idDetallePaquete;
    }

    public void setIdDetallePaquete(Integer idDetallePaquete) {
        this.idDetallePaquete = idDetallePaquete;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public CatArticulosVenta getArticulo() {
        return articulo;
    }

    public void setArticulo(CatArticulosVenta articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
