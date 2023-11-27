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
    private Ticket createTicketWithoutUser(@RequestBody Ticket ticket) {
        return ticketService.create(ticket);
    }

    @PostMapping("create/{username}")
    private Ticket createTicketWithUser(@RequestBody Ticket ticket, @PathVariable String username) {
        return ticketService.create(ticket, username);
    }

    @GetMapping("all")
    private List<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @PutMapping("update")
    private Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("delete")
    private void deleteTicket(@RequestParam String id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping("{id}")
    private Ticket getTicket(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }


}
