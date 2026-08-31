package my.project.sympla_ticket_simple.event;

import lombok.RequiredArgsConstructor;
import my.project.sympla_ticket_simple.event.dto.EventResponseDTO;
import my.project.sympla_ticket_simple.event.dto.EventResquestDTO;
import my.project.sympla_ticket_simple.user.User;
import my.project.sympla_ticket_simple.user.UserNotFoundException;
import my.project.sympla_ticket_simple.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventResponseDTO createEvent(EventResquestDTO eventResquestDTO) {
        User user = userRepository.findById(eventResquestDTO.userId()).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado!"));

        Event event = new Event();
        event.setUser(user);
        event.setStatus(EventStatus.DRAFT);
        event.setName(eventResquestDTO.name());
        event.setDescription(eventResquestDTO.description());
        event.setStartDate(eventResquestDTO.startDate());
        event.setEndDate(eventResquestDTO.endDate());
        event.setLocation(eventResquestDTO.location());
        event.setCategory(eventResquestDTO.category());
        Event eventSaved = eventRepository.save(event);

        return toResponseDTO(eventSaved);
    }

    public EventResponseDTO findById(Long id) {

        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Evento não existente!"));

        return toResponseDTO(event);
    }

    public List<EventResponseDTO> findAll() {
        List<Event> events = eventRepository.findAll();

        return events.stream().map(this::toResponseDTO).toList();
    }

    private EventResponseDTO toResponseDTO(Event event) {
        return new EventResponseDTO(event.getId(), event.getName(), event.getDescription(), event.getStartDate(),
                event.getEndDate(), event.getLocation(), event.getCategory(),
                event.getUser().getId(), event.getStatus());
    }
}
