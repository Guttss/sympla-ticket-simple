package my.project.sympla_ticket_simple.event;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.sympla_ticket_simple.event.dto.EventResponseDTO;
import my.project.sympla_ticket_simple.event.dto.EventResquestDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createdEvent(@Valid @RequestBody EventResquestDTO eventResquestDTO) {
        EventResponseDTO eventResponseDTO = eventService.createEvent(eventResquestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable Long id){
        EventResponseDTO eventResponseDTO = eventService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(eventResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents(){
        List<EventResponseDTO> eventResponseDTOS = eventService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(eventResponseDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody EventResquestDTO eventResquestDTO){
        EventResponseDTO eventResponseDTO = eventService.updateEvent(id, eventResquestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(eventResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
