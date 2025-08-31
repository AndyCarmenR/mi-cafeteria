package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.DetalleTicketDto;
import com.demo.mi_cafeteria.model.dto.TicketDto;
import com.demo.mi_cafeteria.model.entity.CatTipoPago;
import com.demo.mi_cafeteria.model.entity.DetalleTicket;
import com.demo.mi_cafeteria.model.entity.TicketVenta;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.repository.TicketVentaRepository;
import com.demo.mi_cafeteria.utils.BadRequestException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TicketService {

    @Autowired
    private TicketVentaRepository ticketRepo;

    @Autowired
    private UserPwdService pwdService;

    @Autowired
    private DetalleTicketService detalleTicketService;

    @Autowired
    private CatalogService catalogService;

    private String foilTemplate="TCKT-";

    private BigDecimal porcentajeImpuestos=new BigDecimal("0.16");


    public String createNewTicketFolio(Integer ticketId){
        return foilTemplate.concat(String.valueOf(ticketId).length()==1?"0"+String.valueOf(ticketId):String.valueOf(ticketId));
    }

    public TicketDto createNewTicket(TicketDto ticket){
        TicketVenta ticketVenta=new TicketVenta();
        UsuarioPWD usr=pwdService.getAuthenicatedUser();
        ticketVenta.setFechaTicket(LocalDate.now());
        ticketVenta.setActivo(Boolean.TRUE);
        ticketVenta.setUsuarioInfo(usr.getUsuarioInfo());

        ticketRepo.save(ticketVenta);

        ticketVenta.setDetalles(detalleTicketService.createNewDetalleTicket(ticket, ticketVenta));
        ticketVenta.setFolioTicket(createNewTicketFolio(ticketVenta.getIdTicket()));
        ticketVenta.setSubtotal(calcularSubTotal(ticketVenta));
        ticketVenta.setImpuestos(calcularImpuestos(ticketVenta.getSubtotal()));
        ticketVenta.setTotal(calcularTotal(ticketVenta.getSubtotal(),ticketVenta.getImpuestos()));
        ticketVenta.setObservaciones(ticket.getObservaciones());

        ticketRepo.save(ticketVenta);

        return TicketDto.convertToTicketDto(ticketVenta);
    }

    public TicketDto addNewDetalle(DetalleTicketDto ticket){
        TicketVenta ticketVenta=ticketRepo.findById(ticket.getTicketVenta()).orElseThrow(()->new BadRequestException("El ticket que estas buscando no existe, revise sus parametros"));

        DetalleTicket detalleTicket=detalleTicketService.addNewDetalleToTicket(ticket,ticketVenta);
        ticketVenta.getDetalles().add(detalleTicket);
        ticketVenta.setSubtotal(calcularSubTotal(ticketVenta));
        ticketVenta.setImpuestos(calcularImpuestos(ticketVenta.getSubtotal()));
        ticketVenta.setTotal(calcularTotal(ticketVenta.getSubtotal(),ticketVenta.getImpuestos()));

        ticketRepo.save(ticketVenta);
        return TicketDto.convertToTicketDto(ticketVenta);
    }

    public TicketDto cerrarTicket(TicketDto ticket){
        TicketVenta ticketVenta=ticketRepo.findById(ticket.getIdTicket()).orElseThrow(()->new BadRequestException("El ticket que estas buscando no existe, revise sus parametros"));
        CatTipoPago tipoPago = catalogService.getTipoPagoById(ticket.getTipoPago().getIdTipoPago());
        ticketVenta.setTipoPago(tipoPago);
        ticketVenta.setActivo(Boolean.FALSE);
        ticketVenta.setObservaciones(ticket.getObservaciones());
        ticketRepo.save(ticketVenta);
        return TicketDto.convertToTicketDto(ticketVenta);
    }

    private BigDecimal calcularSubTotal(@NotNull TicketVenta ticket){
        BigDecimal subTotal =BigDecimal.ZERO;
        for (DetalleTicket detalleTicket: ticket.getDetalles()){
            subTotal=subTotal.add(detalleTicket.getTotalVenta());
        }
        return subTotal;
    }

    private BigDecimal calcularImpuestos(@NotNull BigDecimal subtotal){
        return porcentajeImpuestos.multiply(subtotal);
    }

    private BigDecimal calcularTotal(@NotNull BigDecimal subtotal, @NotNull BigDecimal impuestos){
        return subtotal.add(impuestos);
    }

}
