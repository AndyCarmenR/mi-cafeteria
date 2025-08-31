package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.DetallePaquete;
import com.demo.mi_cafeteria.model.entity.Paquete;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDto {

    private Integer idPaquete;
    private String nombrePaquete;
    private String descripcion;
    private BigDecimal precio;
    private List<DetallePaqueteDto> detalles;

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
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

    public List<DetallePaqueteDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePaqueteDto> detalles) {
        this.detalles = detalles;
    }

    public static PaqueteDto convertToDto(Paquete paquete){
        PaqueteDto dto=new PaqueteDto();
        dto.setIdPaquete(paquete.getIdPaquete());
        dto.setDescripcion(paquete.getDescripcion());
        dto.setPrecio(paquete.getPrecio());
        dto.setNombrePaquete(paquete.getNombrePaquete());
        List<DetallePaqueteDto>detalles=new ArrayList<>();
        for (DetallePaquete detalle: paquete.getDetalles()){
            detalles.add(DetallePaqueteDto.convertToDto(detalle));
        }
        dto.setDetalles(detalles);

        return dto;
    }
}
