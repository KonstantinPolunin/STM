package com.stm.tickets.repository;

import com.stm.tickets.models.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository {
    List<Ticket> findAvailableTickets(String departure, String destination, String transporter,
                                      LocalDateTime time, int page, int size);
    void buyTicket(Long ticketId, Long userId);
    Ticket findById(Long id);
    List<Ticket> findByUserId(Long userId);

    void deleteTicket(Long id);

    void updateTicket(Long id, Ticket ticket);

    void addTicket(Ticket ticket);
}
