package com.example.ticketing.service;

import com.example.ticketing.dto.TicketRequestDTO;
import com.example.ticketing.dto.TicketResponseDTO;
import com.example.ticketing.entity.Ticket;
import com.example.ticketing.entity.User;
import com.example.ticketing.repository.TicketRepository;
import com.example.ticketing.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    public TicketService(TicketRepository ticketRepository,
                         UserRepository userRepository,
                         AuditLogService auditLogService) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    public TicketResponseDTO createTicket(TicketRequestDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus("OPEN");

        Ticket saved = ticketRepository.save(ticket);

        auditLogService.logAction("TICKET_CREATED", saved, null);

        return new TicketResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getStatus()
        );
    }

    public TicketResponseDTO updateStatus(Long id, String status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(status);
        Ticket updated = ticketRepository.save(ticket);

        auditLogService.logAction("STATUS_UPDATED", updated, null);

        return new TicketResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getStatus()
        );
    }

    public TicketResponseDTO assignTicket(Long ticketId, Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        ticket.setAssignedTo(agent);
        Ticket updated = ticketRepository.save(ticket);

        auditLogService.logAction("TICKET_ASSIGNED", updated, agent);

        return new TicketResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getStatus()
        );
    }
}
