package my.project.sympla_ticket_simple.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank @Size(min=5, max=100)String username,
        @NotBlank @Email @Size(min=15, max=150) String email,
        @NotBlank @Size(min=8, max=50) String password
){}
