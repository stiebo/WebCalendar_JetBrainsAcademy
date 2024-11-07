package webCalendarSpring.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webCalendarSpring.business.Event;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById (Long id);
    List<Event> findEventsByDate (LocalDate date);
    List<Event> findEventsByDateBetween (LocalDate startDate, LocalDate endDate);
}
