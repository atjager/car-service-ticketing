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

    @PostMapping("create/{customer}")
    private Ticket createTicketWithoutUser(@RequestBody Ticket ticket, @PathVariable String customer) {
        return ticketService.create(ticket, customer);
    }

    @PostMapping("create/{username}/{customer}")
    private Ticket createTicketWithUser(@RequestBody Ticket ticket, @PathVariable String username, @PathVariable String customer) {
        return ticketService.create(ticket, username, customer);
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
