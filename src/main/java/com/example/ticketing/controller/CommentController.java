package com.example.ticketing.controller;

import com.example.ticketing.dto.CommentRequestDTO;
import com.example.ticketing.dto.CommentResponseDTO;
import com.example.ticketing.service.CommentService;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/tickets")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @PostMapping("/{ticketId}/comments")
    public CommentResponseDTO addComment(
            @PathVariable Long ticketId,
            @RequestBody CommentRequestDTO dto) {

        return commentService.addComment(ticketId, dto);
    }
}