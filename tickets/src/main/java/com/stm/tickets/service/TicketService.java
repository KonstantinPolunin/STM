package com.stm.tickets.service;

import com.stm.tickets.models.Ticket;
import com.stm.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> findAvailableTickets(String departure, String destination, String transporter,
                                             LocalDateTime time, int page, int size) {
        return ticketRepository.findAvailableTickets(departure, destination, transporter, time, page, size);
    }
    public void buyTicket(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found");
        }
        if (ticket.getIsBought()) {
            throw new IllegalArgumentException("Ticket is already bought");
        }
        ticketRepository.buyTicket(ticketId, userId);
    }
    public List<Ticket> findTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.addTicket(ticket);
    }


    public void updateTicket(Long id, Ticket ticket) {
        ticketRepository.updateTicket(id, ticket);

    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteTicket(id);
    }
}
