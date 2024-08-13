package com.stm.tickets.controller;

import com.stm.tickets.models.Ticket;
import com.stm.tickets.service.TicketService;
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;*/
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//@Api(tags = "Контроллер билетов")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //@ApiOperation(value = "Получение доступных для продажи билетов")
    @GetMapping("/available")
    public List<Ticket> getAvailableTickets(@RequestParam(required = false) String departure,
                                            @RequestParam(required = false) String destination,
                                            @RequestParam(required = false) String transporter,
                                            @RequestParam(required = false) LocalDateTime time,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return ticketService.findAvailableTickets(departure, destination, transporter, time, page, size);
    }

    //@ApiOperation(value = "Покупка билетов пользователем")
    @PostMapping("/{ticketId}/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseTicket(@PathVariable Long ticketId, @RequestParam Long userId) {
        ticketService.buyTicket(ticketId, userId);
    }

    //@ApiOperation(value = "Get user's tickets ")
    @GetMapping("/purchase")
    public List<Ticket> getTicketsByUserId(@RequestParam Long userId) {
        return ticketService.findTicketsByUserId(userId);
    }
}
