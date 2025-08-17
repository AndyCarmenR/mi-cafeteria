package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.CatTipoArticulo;
import jakarta.persistence.Column;

public class TipoArticuloDto {
    private Integer idTipoArticulo;
    private String categoriaArticulo;
    private String descripcionCategoria;

    public Integer getIdTipoArticulo() {
        return idTipoArticulo;
    }

    public void setIdTipoArticulo(Integer idTipoArticulo) {
        this.idTipoArticulo = idTipoArticulo;
    }

    public String getCategoriaArticulo() {
        return categoriaArticulo;
    }

    public void setCategoriaArticulo(String categoriaArticulo) {
        this.categoriaArticulo = categoriaArticulo;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public static TipoArticuloDto convertToDto(CatTipoArticulo tipoArticulo){
        TipoArticuloDto tipoArticuloDto = new TipoArticuloDto();
        tipoArticuloDto.setIdTipoArticulo(tipoArticuloDto.getIdTipoArticulo());
        tipoArticuloDto.setCategoriaArticulo(tipoArticulo.getCategoriaArticulo());
        tipoArticuloDto.setDescripcionCategoria(tipoArticulo.getDescripcionCategoria());
        return tipoArticuloDto;
    }
}
