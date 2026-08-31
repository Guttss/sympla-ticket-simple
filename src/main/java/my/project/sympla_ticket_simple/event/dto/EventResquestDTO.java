package my.project.sympla_ticket_simple.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventResquestDTO(
        @NotBlank @Size(min = 1, max = 100) String name,
        @NotBlank @Size(min = 10) String description,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        @NotBlank @Size(min = 10, max = 100) String location,
        @NotBlank String category,
        @NotNull Long userId
){}