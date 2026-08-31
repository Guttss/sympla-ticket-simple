package my.project.sympla_ticket_simple.event.dto;

import my.project.sympla_ticket_simple.event.EventStatus;


import java.time.LocalDateTime;

public record EventResponseDTO(
    
    Long id,
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String location,
    String category,
    Long userId,
    EventStatus status
){
}
