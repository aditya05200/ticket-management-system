package com.example.ticketing.dto;

public class CommentResponseDTO {

    private Long id;
    private String content;
    private Long ticketId;
    private Long userId;

    public CommentResponseDTO(Long id,
                              String content,
                              Long ticketId,
                              Long userId) {
        this.id = id;
        this.content = content;
        this.ticketId = ticketId;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public Long getTicketId() { return ticketId; }
    public Long getUserId() { return userId; }
}
