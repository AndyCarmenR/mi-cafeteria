package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.dto.DetalleTicketDto;
import com.demo.mi_cafeteria.model.dto.TicketDto;
import com.demo.mi_cafeteria.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<TicketDto> createNewTicket(@RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.createNewTicket(ticketDto));
    }

    @PatchMapping("/detalle")
    public ResponseEntity<TicketDto> addNewDetalle(@RequestBody DetalleTicketDto detalle){
        return ResponseEntity.ok(ticketService.addNewDetalle(detalle));
    }
    @PutMapping("/ticket")
    public ResponseEntity<TicketDto>cerrarTicket(@RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.cerrarTicket(ticketDto));
    }


}
