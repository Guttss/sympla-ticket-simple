package my.project.sympla_ticket_simple.event;

import lombok.RequiredArgsConstructor;
import my.project.sympla_ticket_simple.event.dto.EventResponseDTO;
import my.project.sympla_ticket_simple.event.dto.EventResquestDTO;
import my.project.sympla_ticket_simple.shared.BusinessException;
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

    public EventResponseDTO updateEvent(Long id, EventResquestDTO eventResquestDTO) {
        Event event =  eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Evento não existente!"));

        if(event.getStatus().equals(EventStatus.PUBLISHED) || event.getStatus().equals(EventStatus.COMPLETED)) {
            throw new BusinessException("Eventos publicados ou finalizados não podem ser alterados!");
        }

        event.setName(eventResquestDTO.name());
        event.setDescription(eventResquestDTO.description());
        event.setStartDate(eventResquestDTO.startDate());
        event.setEndDate(eventResquestDTO.endDate());
        event.setLocation(eventResquestDTO.location());
        event.setCategory(eventResquestDTO.category());
        Event eventUpdated = eventRepository.save(event);
        return toResponseDTO(eventUpdated);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Evento não existente!"));

        if(event.getStatus().equals(EventStatus.PUBLISHED) || event.getStatus().equals(EventStatus.COMPLETED)) {
            throw new BusinessException("Eventos publicados ou finalizados não podem ser alterados!");
        }

        eventRepository.delete(event);
    }

    private EventResponseDTO toResponseDTO(Event event) {
        return new EventResponseDTO(event.getId(), event.getName(), event.getDescription(), event.getStartDate(),
                event.getEndDate(), event.getLocation(), event.getCategory(),
                event.getUser().getId(), event.getStatus());
    }
}
