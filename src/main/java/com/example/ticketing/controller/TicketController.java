package com.example.ticketing.controller;

import com.example.ticketing.dto.TicketRequestDTO;
import com.example.ticketing.dto.TicketResponseDTO;
import com.example.ticketing.dto.TicketStatusUpdateDTO;
import com.example.ticketing.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public TicketResponseDTO createTicket(@RequestBody TicketRequestDTO dto) {
        return ticketService.createTicket(dto);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('AGENT')")
    public TicketResponseDTO updateTicketStatus(
            @PathVariable Long id,
            @RequestBody TicketStatusUpdateDTO dto) {

        return ticketService.updateStatus(id, dto.getStatus());
    }

    @PutMapping("/{id}/assign/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public TicketResponseDTO assignTicket(
            @PathVariable Long id,
            @PathVariable Long agentId) {

        return ticketService.assignTicket(id, agentId);
    }

}
