package my.project.sympla_ticket_simple.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserId(Long userId);

    List<Event> findByStatus(EventStatus status);
}
