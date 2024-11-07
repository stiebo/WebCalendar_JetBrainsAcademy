package webCalendarSpring.presentation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webCalendarSpring.business.Event;
import webCalendarSpring.business.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
public class WebCalendarController {
    @Autowired
    EventService eventService;

    @GetMapping("/event")
    public ResponseEntity<?> getEvents(
            @RequestParam(value = "start_time", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "end_time", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Event> events;
        if (startDate == null || endDate == null) {
            events = eventService.getAllEvents();
        }
        else {
            events = eventService.findEventsByDateBetween(startDate, endDate);
        }
        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<?> getAllEvents(@PathVariable("id") Long id) {
        Event event = eventService.findEventById(id);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message", "The event doesn't exist!"));
        }
        return ResponseEntity.ok(event);
    }

    @GetMapping("/event/today")
    public ResponseEntity<?> getTodaysEvents() {
        List<Event> events = eventService.findEventsByDate(LocalDate.now());
        return ResponseEntity.ok(events);
    }

    @PostMapping("/event")
    public ResponseEntity<?> addEvent(@RequestBody CreateEventDTO eventDTO) {
        if (eventDTO.getEvent() == null || eventDTO.getEvent().trim().isEmpty() ||
                eventDTO.getDate() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        String event = eventDTO.getEvent();
        LocalDate date;
        try {
            date = LocalDate.parse(eventDTO.getDate());
        }
        catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
        Event newEvent = eventService.save(new Event(event, date));
        return ResponseEntity.ok().body(new EventResponseDTO("The event has been added!",
                event, date.toString()));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        Event event = eventService.deleteEventById(id);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message", "The event doesn't exist!"));
        }
        return ResponseEntity.ok(event);
    }

}
