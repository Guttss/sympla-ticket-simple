package my.project.sympla_ticket_simple.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.project.sympla_ticket_simple.event.Event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=50)
    private String name;

    @Column(length=200)
    private String description;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(nullable=false)
    private Integer totalQuantity;

    @Column(nullable=false)
    private Integer soldQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TicketStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id", nullable=false)
    private Event event;

    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getAvailableQuantity() {
        return totalQuantity - soldQuantity;
    }
}
