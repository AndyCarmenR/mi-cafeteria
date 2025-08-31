package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.*;
import com.demo.mi_cafeteria.model.entity.*;
import com.demo.mi_cafeteria.repository.*;
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

    @Autowired
    private DescuentoArticuloRepository descuentoArticuloRepository;

    @Autowired
    private CatExtrasRepository extrasRepository;

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private DetallePaqueteRepository detallePaqueteRepository;

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
        CatTipoArticulo articuloSaved=tipoArticuloRepository.save(catTipoArticulo);
        return TipoArticuloDto.convertToDto(articuloSaved);
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

    public List<DescuentoArticuloDto> getAllDescuentoArticulo(){
        List<DescuentoArticuloDto>descuentos=new ArrayList<>();
        List<DescuentoArticulo>descuentosList=descuentoArticuloRepository.findAll();
        if (descuentosList.isEmpty()) {
            throw new NotFoundException("Aun no hay un catalogo registrado");
        }
        for (DescuentoArticulo descuento:descuentosList){
            descuentos.add(DescuentoArticuloDto.convertToDto(descuento));
        }
        return descuentos;
    }
    public DescuentoArticuloDto saveDescuento(DescuentoArticuloDto descuento){
        if (descuento.getDescuentoPorcentaje() == null ||descuento.getNombreDescuento().isEmpty()|| descuento.getDescripcion().isEmpty()) {
            throw new BadRequestException("parametros insuficientes");
        }
        DescuentoArticulo descuentoArticulo=new DescuentoArticulo();
        descuentoArticulo.setDescuentoPorcentaje(descuento.getDescuentoPorcentaje());
        descuentoArticulo.setNombreDescuento(descuento.getNombreDescuento());
        descuentoArticulo.setDescripcion(descuento.getDescripcion());

        descuentoArticuloRepository.save(descuentoArticulo);
        return DescuentoArticuloDto.convertToDto(descuentoArticulo);
    }


    public List<ExtrasDto> getAllExtras(){
        List<ExtrasDto> extras =new ArrayList<>();
        List<CatExtras> extrasList =extrasRepository.findAll();
        if (extrasList.isEmpty()) {
            throw new NotFoundException("Aun no hay un catalogo registrado");
        }
        for (CatExtras catExtra: extrasList){
            extras.add(ExtrasDto.convertToDto(catExtra));
        }
        return extras;
    }
    public ExtrasDto saveExtra(ExtrasDto extra){
        if (extra.getDescripcion().isEmpty() ||extra.getNombreExtra().isEmpty()|| extra.getPrecioExtra()==null) {
            throw new BadRequestException("parametros insuficientes");
        }
        CatExtras catExtras=new CatExtras();
        catExtras.setNombreExtra(extra.getNombreExtra());
        catExtras.setDescripcion(extra.getDescripcion());
        catExtras.setPrecioExtra(extra.getPrecioExtra());

        extrasRepository.save(catExtras);
        return ExtrasDto.convertToDto(catExtras);
    }

    public CatTipoPago getTipoPagoById(Integer id){
        return tipoPagoRepository.findById(id).orElseThrow(()-> new NotFoundException("No hemos encontrado el tipo de pago que buscas"));
    }


    public List<PaqueteDto>getAllPaquetes(){
        List<PaqueteDto>dtoList=new ArrayList<>();
        List<Paquete>paquetes=paqueteRepository.findAll();
        if (paquetes.isEmpty()){
            throw new NotFoundException("Aun no hay paquetes registrados");
        }
        for (Paquete paquete:paquetes){
            dtoList.add(PaqueteDto.convertToDto(paquete));
        }
        return dtoList;
    }

    public PaqueteDto saveNewPaquete(PaqueteDto paqueteDto){
        if (paqueteDto.getNombrePaquete().isEmpty()
                || paqueteDto.getDescripcion().isEmpty()
                || paqueteDto.getPrecio().equals(BigDecimal.ZERO)
                || paqueteDto.getDetalles().isEmpty()){
            throw new BadRequestException("parametros insuficientes");
        }
        Paquete paquete=new Paquete();
        paquete.setNombrePaquete(paqueteDto.getNombrePaquete());
        paquete.setDescripcion(paqueteDto.getDescripcion());
        paquete.setPrecio(paqueteDto.getPrecio());
        addDetallestoPaquete(paqueteDto.getDetalles(),paquete);
        paqueteRepository.save(paquete);
        return PaqueteDto.convertToDto(paquete);
    }

    private void addDetallestoPaquete(List<DetallePaqueteDto> detallesDto, Paquete paquete){
        for (DetallePaqueteDto dto:detallesDto){
            DetallePaquete detallePaquete=new DetallePaquete();
            Optional<CatArticulosVenta> optArticulo=articulosVentaRepository.findById(dto.getArticulo().getIdArticuloVenta());
            if (optArticulo.isEmpty()){
                throw new NotFoundException("articulo no encontrado");
            }
            detallePaquete.setPaquete(paquete);
            detallePaquete.setArticulo(optArticulo.get());
            detallePaquete.setCantidad(dto.getCantidad());
            paquete.addDetalle(detallePaquete);
        }
    }
    public Paquete getPaqueteById(Integer id){
        return paqueteRepository.findById(id).orElseThrow(()-> new NotFoundException("No hemos encontrado el tipo de pago que buscas"));
    }
}