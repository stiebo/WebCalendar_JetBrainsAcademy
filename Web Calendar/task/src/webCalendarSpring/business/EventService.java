package webCalendarSpring.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webCalendarSpring.persistence.EventRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService (EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findEventById(Long id) {
        return eventRepository.findEventById(id);
    }

    public Event save(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> findEventsByDate(LocalDate date) {
        return eventRepository.findEventsByDate(date);
    }

    public List<Event> findEventsByDateBetween(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findEventsByDateBetween(startDate, endDate);
    }

    public boolean existsEventById(Long id) {
        return eventRepository.existsById(id);
    }

    public Event deleteEventById(Long id) {
        Event event = findEventById(id);
        if (event != null) {
            eventRepository.deleteById(id);
        }
        return event;
    }
}
