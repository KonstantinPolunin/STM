package com.stm.tickets.controller;

import com.stm.tickets.models.Rout;
import com.stm.tickets.models.Ticket;
import com.stm.tickets.models.Transporter;
import com.stm.tickets.models.User;
import com.stm.tickets.service.RoutService;
import com.stm.tickets.service.TicketService;
import com.stm.tickets.service.TransporterService;
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;*/
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(tags = "Контроллер админа")
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final TicketService ticketService;
    private final RoutService routService;
    private final TransporterService transporterService;


    public AdminController(TicketService ticketService, RoutService routService, TransporterService transporterService) {
        this.ticketService = ticketService;
        this.routService = routService;
        this.transporterService = transporterService;
    }

    @GetMapping
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Admin page");
    }

    //@ApiOperation(value = "Добавление билета")
    @PostMapping("/tickets")
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticket) {
        try {
            ticketService.addTicket(ticket);
            return ResponseEntity.ok("Ticket added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding ticket: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Изменение существующего билета")
    @PutMapping("/tickets/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        try {
            ticketService.updateTicket(id, ticket);
            return ResponseEntity.ok("Ticket updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating ticket: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Удаление билета")
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        try {
            ticketService.deleteTicket(id);
            return ResponseEntity.ok("Ticket deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting ticket: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Добавление маршрута")
    @PostMapping("/routes")
    public ResponseEntity<String> addRout(@RequestBody Rout rout) {
        try {
            routService.addRout(rout);
            return ResponseEntity.ok("Route added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding route: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Изменение существующего маршрута")
    @PutMapping("/routes/{id}")
    public ResponseEntity<String> updateRout(@PathVariable Long id, @RequestBody Rout rout) {
        try {
            routService.updateRout(id, rout);
            return ResponseEntity.ok("Rout updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating rout: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Удаление маршрута")
    @DeleteMapping("/routes/{id}")
    public ResponseEntity<String> deleteRout(@PathVariable Long id) {
        try {
            routService.deleteRout(id);
            return ResponseEntity.ok("Rout deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting rout: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Добавление нового перевозчика")
    @PostMapping("/carriers")
    public ResponseEntity<String> addTransporter(@RequestBody Transporter transporter) {
        try {
            transporterService.addTransporter(transporter);
            return ResponseEntity.ok("Carrier added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding carrier: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Изменение существующего перевозчика")
    @PutMapping("/carriers/{id}")
    public ResponseEntity<String> updateTransporter(@PathVariable Long id, @RequestBody Transporter transporter) {
        try {
            transporterService.updateTransporter(id, transporter);
            return ResponseEntity.ok("Carrier updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating transporter: " + e.getMessage());
        }
    }

    //@ApiOperation(value = "Удаление перевозчика")
    @DeleteMapping("/carriers/{id}")
    public ResponseEntity<String> deleteCarrier(@PathVariable Long id) {
        try {
            transporterService.deleteTransporter(id);
            return ResponseEntity.ok("Carrier deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting carrier: " + e.getMessage());
        }
    }
}
