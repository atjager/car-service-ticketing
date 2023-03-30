package com.aj.carserviceticketing.service;

import com.aj.carserviceticketing.domain.ticket.Ticket;
import com.aj.carserviceticketing.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Ticket ticket) {
        Ticket ticketOld = ticketRepository.findById(ticket.getId()).get();
        ticket.setCreated(ticketOld.getCreated());
        return ticketRepository.save(ticket);
    }
}
