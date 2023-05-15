package com.aj.carserviceticketing.repository;

import com.aj.carserviceticketing.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findById(UUID uuid);

    void deleteById(UUID uuid);
}
