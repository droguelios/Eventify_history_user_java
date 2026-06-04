package com.example.Eventify.service;

import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.entities.Event;
import com.example.Eventify.exceptions.ResourceNotFoundException;
import com.example.Eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public Slice<EventSummaryDTO> searchEvents(String city, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepository.findEventsWithFilters(city, category, pageable);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public void insert(Event event) {
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new RuntimeException("The event name cannot be empty");
        }
        eventRepository.save(event);
    }

    public void delete(Long id) {
        if (id == null || !eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found for delete id: " + id);
        }
        eventRepository.deleteById(id);
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