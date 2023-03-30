package com.aj.carserviceticketing.controller;

import com.aj.carserviceticketing.domain.ticket.Ticket;
import com.aj.carserviceticketing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ticket/")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("create")
    private Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.create(ticket);
    }

    @GetMapping("all")
    private List<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @PutMapping("update")
    private Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }


}
