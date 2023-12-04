package com.aj.carserviceticketing.service;

import com.aj.carserviceticketing.domain.ticket.Ticket;
import com.aj.carserviceticketing.repository.TicketRepository;
import com.aj.carserviceticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    public Ticket create(Ticket ticket, String username) {
        Ticket ticket1 = ticket;
        ticket1.setUser(userRepository.findByUsername(username).get());
        return ticketRepository.save(ticket1);
    }

    public Ticket create(Ticket ticket) {
        ticket.setUser(null);
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

    public void deleteTicket(String id) {
        ticketRepository.delete(ticketRepository.findById(UUID.fromString(id)).get());
    }

    public Ticket getTicketById(String id) {
        return ticketRepository.findById(UUID.fromString(id)).get();
    }
}
