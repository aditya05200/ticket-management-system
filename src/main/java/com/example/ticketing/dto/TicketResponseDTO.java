package com.example.ticketing.dto;

public class TicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;

    public TicketResponseDTO(Long id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
}
