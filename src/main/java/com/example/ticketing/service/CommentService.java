package com.example.ticketing.service;

import com.example.ticketing.dto.CommentRequestDTO;
import com.example.ticketing.dto.CommentResponseDTO;
import com.example.ticketing.entity.Comment;
import com.example.ticketing.entity.Ticket;
import com.example.ticketing.entity.User;
import com.example.ticketing.repository.CommentRepository;
import com.example.ticketing.repository.TicketRepository;
import com.example.ticketing.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          TicketRepository ticketRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public CommentResponseDTO addComment(Long ticketId,
                                         CommentRequestDTO dto) {

        //  Ticket
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        //  Logged-in user
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Save comment
        Comment comment = new Comment();
        comment.setTicket(ticket);
        comment.setUser(user);
        comment.setContent(dto.getContent());

        Comment saved = commentRepository.save(comment);

        //  Response
        return new CommentResponseDTO(
                saved.getId(),
                saved.getContent(),
                ticket.getId(),
                user.getId()
        );
    }
}
