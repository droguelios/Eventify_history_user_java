package com.example.Eventify.service;

import org.springframework.stereotype.Service;
import com.example.Eventify.entities.Event;
import com.example.Eventify.exceptions.ResourceNotFoundException;
import com.example.Eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page; // <--- Añade este
import org.springframework.data.domain.Pageable; // <--- Cambia el import

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public void insert(Event event) {
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new RuntimeException("The event name cannot be empty");
        }
        eventRepository.save(event);
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public void update(Event event) {
        if (event.getId() == null || !eventRepository.existsById(event.getId())) {
            throw new ResourceNotFoundException("Event not found for update" + event.getId());
        }
        eventRepository.save(event);
    }

    public Event findByid(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not found with this id : " + id));
    }

}
