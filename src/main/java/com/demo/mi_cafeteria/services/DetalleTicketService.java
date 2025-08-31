package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.DetalleTicketDto;
import com.demo.mi_cafeteria.model.dto.ExtrasDto;
import com.demo.mi_cafeteria.model.dto.TicketDto;
import com.demo.mi_cafeteria.model.entity.*;
import com.demo.mi_cafeteria.repository.*;
import com.demo.mi_cafeteria.utils.BadRequestException;
import com.demo.mi_cafeteria.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleTicketService {

    @Autowired
    private DetalleticketRepository detalleticketRepository;
    @Autowired
    private CatExtrasRepository extrasRepos;
    @Autowired
    private CatArticulosVentaRepository articulosVentaRepo;
    @Autowired
    private DescuentoArticuloRepository descuentoArticuloRepo;
    @Autowired
    private TicketVentaRepository ticketVentaRepository;
    @Autowired
    private CatalogService catalogService;

    public List<DetalleTicket>createNewDetalleTicket(TicketDto ticketDto,TicketVenta ticket){
        List<DetalleTicket>detalles=new ArrayList<>();


        List<CatExtras>extrasList=new ArrayList<>();
        for (DetalleTicketDto dto:ticketDto.getDetalles()){
            DetalleTicket dt=new DetalleTicket();
            dt.setTicketVenta(ticket);
            Optional<CatArticulosVenta> avo;
            try {
                avo=articulosVentaRepo.findById(dto.getArticuloVenta().getIdArticuloVenta());
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
            if (avo.isEmpty()) {
                Paquete paquete=catalogService.getPaqueteById(dto.getPaquete().getIdPaquete());
                if (paquete == null) {
                    throw new NotFoundException("El articulo o paquete que estas buscando no existe, por favor revisa tus parametros");
                }
                dt.setPaquete(paquete);
            }else {
                dt.setArticuloVenta(avo.get());
            }
            Optional<DescuentoArticulo> descOpt;
            try {
                descOpt=descuentoArticuloRepo.findById(dto.getDescuento().getIdDescuento());
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
            descOpt.ifPresent(dt::setDescuentoArticulo);
            for (ExtrasDto e: dto.getExtras()){
                Optional<CatExtras>extrasOptional;
                try {
                    extrasOptional=extrasRepos.findById(e.getIdExtra());
                } catch (Exception ex) {
                    throw new BadRequestException(ex.getMessage());
                }
                extrasOptional.ifPresent(extrasList::add);
            }
            dt.setExtras(extrasList);
            dt.setCantidadArticulos(dto.getCantidadArticulos());
            dt.setTotalVenta(calcularTotal(dt));
        }
        detalleticketRepository.saveAll(detalles);

        return detalles;
    }

    public DetalleTicket addNewDetalleToTicket(DetalleTicketDto dto,TicketVenta ticket){
        DetalleTicket dt=new DetalleTicket();
        dt.setTicketVenta(ticket);
        Optional<CatArticulosVenta> avo;
        try {
            avo=articulosVentaRepo.findById(dto.getArticuloVenta().getIdArticuloVenta());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        if (avo.isEmpty()) {
            throw new NotFoundException("El articulo que estas buscando no existe, por favor revisa tus parametros");
        }
        dt.setArticuloVenta(avo.get());
        Optional<DescuentoArticulo> descOpt;
        try {
            descOpt=descuentoArticuloRepo.findById(dto.getDescuento().getIdDescuento());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        descOpt.ifPresent(dt::setDescuentoArticulo);
        List<CatExtras> extrasList=new ArrayList<>();
        for (ExtrasDto e: dto.getExtras()) {
            Optional<CatExtras> extrasOptional;
            try {
                extrasOptional = extrasRepos.findById(e.getIdExtra());
            } catch (Exception ex) {
                throw new BadRequestException(ex.getMessage());
            }
            extrasOptional.ifPresent(extrasList::add);
        }
        dt.setExtras(extrasList);
        dt.setCantidadArticulos(dto.getCantidadArticulos());
        dt.setTotalVenta(calcularTotal(dt));
        return dt;
    }

    private BigDecimal calcularTotal(DetalleTicket dt){
        BigDecimal total=BigDecimal.valueOf((long) dt.getArticuloVenta().getPrecioArticulo().intValue() * dt.getCantidadArticulos());
        BigDecimal totalExtras=BigDecimal.ZERO;
        for(CatExtras extra:dt.getExtras()){
            totalExtras=totalExtras.add(extra.getPrecioExtra());
        }
        total=total.add(totalExtras);
        BigDecimal descuento=BigDecimal.ZERO;
        if (dt.getDescuentoArticulo() == null) {

            descuento=total.multiply((dt.getDescuentoArticulo().getDescuentoPorcentaje().divide(BigDecimal.valueOf(100), RoundingMode.CEILING)));
        }
        total=total.subtract(descuento);
        return total;
    }
}
