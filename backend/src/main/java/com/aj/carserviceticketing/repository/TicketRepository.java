package com.aj.carserviceticketing.repository;

import com.aj.carserviceticketing.domain.ticket.Ticket;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Hidden
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findById(UUID uuid);

    void deleteById(UUID uuid);
}
