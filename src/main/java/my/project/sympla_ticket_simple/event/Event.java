package my.project.sympla_ticket_simple.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.project.sympla_ticket_simple.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable=false, length=100)
    private String name;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String  description;

    @Column(name = "start_date", nullable=false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable=false)
    private LocalDateTime endDate;

    @Column(name="created_at", nullable=false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable=false, length=200)
    private String location;

    @Column(nullable=false)
    private String category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private EventStatus status;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
