package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.requests.TicketRequest;
import com.demo.mi_cafeteria.model.responses.Detalle;
import com.demo.mi_cafeteria.model.responses.TicketResponse;
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
    public ResponseEntity<TicketResponse> createNewTicket(@RequestBody TicketRequest ticket){
        return ResponseEntity.ok(ticketService.createNewTicket(ticket));
    }

    @PatchMapping("/detalle")
    public ResponseEntity<TicketResponse> addNewDetalle(@RequestBody Detalle detalle){
        return ResponseEntity.ok(ticketService.addNewDetalle(detalle));
    }
    @PutMapping("/ticket")
    public ResponseEntity<TicketResponse>cerrarTicket(@RequestBody TicketRequest ticket){
        return ResponseEntity.ok(ticketService.cerrarTicket(ticket));
    }


}
