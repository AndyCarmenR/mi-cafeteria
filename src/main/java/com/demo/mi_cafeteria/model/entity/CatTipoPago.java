package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

@Entity(name = "CAT_TIPO_PAGO")
public class CatTipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PAGO")
    private Integer idTipoPago;

    @Column(name = "TIPO_PAGO")
    private String tipoPago;

    @Column(name = "DESCRIPCION_PAGO")
    private String descripcionPago;

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }
}
