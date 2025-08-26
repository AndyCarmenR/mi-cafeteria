package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.ArticuloVentaDto;
import com.demo.mi_cafeteria.model.dto.TipoArticuloDto;
import com.demo.mi_cafeteria.model.dto.TipoPagoDto;
import com.demo.mi_cafeteria.model.entity.CatArticulosVenta;
import com.demo.mi_cafeteria.model.entity.CatTipoArticulo;
import com.demo.mi_cafeteria.model.entity.CatTipoPago;
import com.demo.mi_cafeteria.repository.CatArticulosVentaRepository;
import com.demo.mi_cafeteria.repository.CatTipoArticuloRepository;
import com.demo.mi_cafeteria.repository.CatTipoPagoRepository;
import com.demo.mi_cafeteria.utils.BadRequestException;
import com.demo.mi_cafeteria.utils.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private CatArticulosVentaRepository articulosVentaRepository;

    @Autowired
    private CatTipoPagoRepository tipoPagoRepository;

    @Autowired
    private CatTipoArticuloRepository tipoArticuloRepository;

    public List<TipoPagoDto> getAllTipoPago(){
        List<CatTipoPago> catTipoPagoList=tipoPagoRepository.findAll();
        if (catTipoPagoList.isEmpty()) {
            throw new NotFoundException("Aun no hay un catalogo registrado");
        }
        List<TipoPagoDto> tipoPagoDtoList=new ArrayList<>();
        for(CatTipoPago tipoPago:catTipoPagoList){
            tipoPagoDtoList.add(TipoPagoDto.convertToDto(tipoPago));
        }

        return tipoPagoDtoList;
    }

    public List<ArticuloVentaDto> getAllArticulosVenta(){
        List<CatArticulosVenta> catArticulosVentaList=articulosVentaRepository.findAll();
        if (catArticulosVentaList.isEmpty()) {
            throw new NotFoundException("Aun no hay un catalogo registrado");
        }
        List<ArticuloVentaDto>articuloVentaDtoList=new ArrayList<>();
        for(CatArticulosVenta articuloVenta:catArticulosVentaList){
            articuloVentaDtoList.add(ArticuloVentaDto.convertToArticuloVentaDto(articuloVenta));
        }
        return articuloVentaDtoList;
    }

    public List<TipoArticuloDto>getAllTipoArticulo(){
        List<CatTipoArticulo>catTipoArticuloList=tipoArticuloRepository.findAll();
        if (catTipoArticuloList.isEmpty()) {
            throw new NotFoundException("Aun no hay un catalogo registrado");
        }
        List<TipoArticuloDto>tipoArticuloDtoList=new ArrayList<>();
        for (CatTipoArticulo tipoArticulo:catTipoArticuloList){
            tipoArticuloDtoList.add(TipoArticuloDto.convertToDto(tipoArticulo));
        }

        return tipoArticuloDtoList;
    }
    public TipoPagoDto saveTipoPago(TipoPagoDto tipoPagoDto){
        if (tipoPagoDto.getTipoPago().isEmpty() || tipoPagoDto.getDescripcion().isEmpty()) {
            throw new RuntimeException("la descripcion o el tipo de pago estan vacías, revise sus parámetros");
        }
        CatTipoPago catTipoPago=new CatTipoPago();
        catTipoPago.setTipoPago(tipoPagoDto.getTipoPago());
        catTipoPago.setDescripcionPago(tipoPagoDto.getDescripcion());
        tipoPagoRepository.save(catTipoPago);
        return TipoPagoDto.convertToDto(catTipoPago);
    }

    public TipoArticuloDto saveTipoArticulo(TipoArticuloDto tipoArticuloDto){
        CatTipoArticulo catTipoArticulo=new CatTipoArticulo();
        if (tipoArticuloDto.getCategoriaArticulo().isEmpty() || tipoArticuloDto.getDescripcionCategoria().isEmpty()) {
            throw new BadRequestException("la descripocion o la categoria estan vacias, revise sus parametros");
        }
        catTipoArticulo.setCategoriaArticulo(tipoArticuloDto.getCategoriaArticulo());
        catTipoArticulo.setDescripcionCategoria(tipoArticuloDto.getDescripcionCategoria());
        tipoArticuloRepository.save(catTipoArticulo);
        return TipoArticuloDto.convertToDto(catTipoArticulo);
    }
    public ArticuloVentaDto saveArticuloVenta(ArticuloVentaDto articuloVentaDto) throws BadRequestException {
        CatArticulosVenta catArticulosVenta=new CatArticulosVenta();
        if (articuloVentaDto.getDescripcionArticulo().isEmpty()|| articuloVentaDto.getNombreArticulo().isEmpty() || articuloVentaDto.getPrecioArticulo()==null
        || articuloVentaDto.getTipoArticulo().getIdTipoArticulo()==null) {
            throw new BadRequestException("Los parametros requeridos estan incompletos, por favor reviselos");
        }
       CatTipoArticulo catTipoArticulo=tipoArticuloRepository.findById(articuloVentaDto.getTipoArticulo().getIdTipoArticulo()).orElseThrow(()-> new NotFoundException("no se ha encontrado el Tipo de articulo requerido"));

        catArticulosVenta.setDescripcionArticulo(articuloVentaDto.getDescripcionArticulo());
        catArticulosVenta.setNombreArticulo(articuloVentaDto.getNombreArticulo());
        catArticulosVenta.setPrecioArticulo(articuloVentaDto.getPrecioArticulo());
        catArticulosVenta.setTipoArticulo(catTipoArticulo);

        articulosVentaRepository.save(catArticulosVenta);

        return ArticuloVentaDto.convertToArticuloVentaDto(catArticulosVenta);
    }
}
