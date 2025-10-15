package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.entity.CatTipoPago;
import com.demo.mi_cafeteria.model.entity.DetalleTicket;
import com.demo.mi_cafeteria.model.entity.TicketVenta;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.model.requests.TicketRequest;
import com.demo.mi_cafeteria.model.responses.Detalle;
import com.demo.mi_cafeteria.model.responses.TicketResponse;
import com.demo.mi_cafeteria.repository.TicketVentaRepository;
import com.demo.mi_cafeteria.repository.UsuarioInfoRepository;
import com.demo.mi_cafeteria.utils.BadRequestException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @Autowired
    private UsuarioInfoRepository repository;

    private String foilTemplate="TCKT-";

    private BigDecimal porcentajeImpuestos=new BigDecimal("0.16");


    public String createNewTicketFolio(Integer ticketId){
        return foilTemplate.concat(String.valueOf(ticketId).length()==1?"0"+String.valueOf(ticketId):String.valueOf(ticketId));
    }

    public TicketResponse createNewTicket(TicketRequest ticket){
        TicketVenta ticketVenta=new TicketVenta();
        UsuarioPWD usr=pwdService.getAuthenicatedUser();
        ticketVenta.setFechaTicket(LocalDate.now());
        ticketVenta.setActivo(Boolean.TRUE);
        ticketVenta.setUsuarioInfo(usr.getUsuarioInfo());

        ticketRepo.save(ticketVenta);
        List<DetalleTicket>detalles= detalleTicketService.createNewDetalleTicket(ticket, ticketVenta);

        ticketVenta.setDetalles(detalles);

        ticketVenta.setFolioTicket(createNewTicketFolio(ticketVenta.getIdTicket()));
        ticketVenta.setSubtotal(calcularSubTotal(ticketVenta));
        ticketVenta.setImpuestos(calcularImpuestos(ticketVenta.getSubtotal()));
        ticketVenta.setTotal(calcularTotal(ticketVenta.getSubtotal(),ticketVenta.getImpuestos()));
        ticketVenta.setObservaciones(ticket.getObservaciones());

        ticketRepo.save(ticketVenta);

        return new TicketResponse(ticketVenta);
    }

    public TicketResponse addNewDetalle(Detalle ticket){
        TicketVenta ticketVenta=ticketRepo.findById(ticket.getIdTicketVenta()).orElseThrow(()->new BadRequestException("El ticket que estas buscando no existe, revise sus parametros"));

        DetalleTicket detalleTicket=detalleTicketService.addNewDetalleToTicket(ticket,ticketVenta);
        ticketVenta.getDetalles().add(detalleTicket);
        ticketVenta.setSubtotal(calcularSubTotal(ticketVenta));
        ticketVenta.setImpuestos(calcularImpuestos(ticketVenta.getSubtotal()));
        ticketVenta.setTotal(calcularTotal(ticketVenta.getSubtotal(),ticketVenta.getImpuestos()));

        ticketRepo.save(ticketVenta);
        return new TicketResponse(ticketVenta);
    }

    public TicketResponse cerrarTicket(TicketRequest ticket){
        TicketVenta ticketVenta=ticketRepo.findById(ticket.getIdTicket()).orElseThrow(()->new BadRequestException("El ticket que estas buscando no existe, revise sus parametros"));
        CatTipoPago tipoPago = catalogService.getTipoPagoById(ticket.getIdTipoPago());
        ticketVenta.setTipoPago(tipoPago);
        ticketVenta.setActivo(Boolean.FALSE);
        ticketVenta.setObservaciones(ticket.getObservaciones());
        ticketRepo.save(ticketVenta);
        return new TicketResponse(ticketVenta);
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
