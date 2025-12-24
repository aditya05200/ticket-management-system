package com.example.ticketing.service;

import com.example.ticketing.entity.AuditLog;
import com.example.ticketing.entity.Ticket;
import com.example.ticketing.entity.User;
import com.example.ticketing.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logAction(String action, Ticket ticket, User user) {

        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setTicket(ticket);
        log.setPerformedBy(user);
        log.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}
