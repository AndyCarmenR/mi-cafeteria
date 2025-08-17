package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

@Entity(name = "CAT_TIPO_ARTICULO")
public class CatTipoArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ARTICULO")
    private Integer idTipoArticulo;

    @Column(name = "CATEGORIA_ARTICULO")
    private String categoriaArticulo;

    @Column(name = "DESCRIPICON_CATEGORIA")
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
}
