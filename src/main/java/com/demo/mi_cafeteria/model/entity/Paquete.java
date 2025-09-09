package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PAQUETE")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAQUETE")
    private Integer idDPaquete;

    @Column(name = "NOMBRE_PAQUETE")
    private String nombrePaquete;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO_PAQUETE",precision = 10, scale = 2)
    private BigDecimal precio;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePaquete> detalles=new ArrayList<>();

    public Integer getIdPaquete() {
        return idDPaquete;
    }

    public void setIdPaquete(Integer idDPaquete) {
        this.idDPaquete = idDPaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<DetallePaquete> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePaquete> detalles) {
        this.detalles = detalles;
    }
    public void addDetalle(DetallePaquete detalle) {
        detalles.add(detalle);
        detalle.setPaquete(this);
    }

    public void removeDetalle(DetallePaquete detalle) {
        detalles.remove(detalle);
        detalle.setPaquete(null);
    }
}
