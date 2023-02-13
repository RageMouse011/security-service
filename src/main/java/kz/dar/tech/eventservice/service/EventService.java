package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.entity.Event;
import kz.dar.tech.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    public List<Event> getEventsBySection(String section) {
        return eventRepository.findBySection(section);
    }

    public Event postEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).get();
    }
}
