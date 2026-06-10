package com.example.Eventify.service;

import com.example.Eventify.dto.EventCreateDTO;
import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.dto.EventUpdateDTO;
import com.example.Eventify.entities.Event;
import com.example.Eventify.exceptions.ResourceNotFoundException;
import com.example.Eventify.repository.EventMapper;
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
    private final EventMapper eventMapper;

    @Transactional(readOnly = true)
    public Slice<EventSummaryDTO> searchEvents(String city, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepository.findEventsWithFilters(city, category, pageable);
    }

    @Transactional(readOnly = true)
    public List<EventSummaryDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toSummaryDTO)
                .toList();
    }

    public Page<EventSummaryDTO> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable).map(eventMapper::toSummaryDTO);
    }

    @Transactional
    public EventSummaryDTO insert(EventCreateDTO createDTO) {
        Event event = eventMapper.toEntity(createDTO);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toSummaryDTO(savedEvent);
    }

    @Transactional
    public void delete(Long id) {
        if (id == null || !eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found for delete id: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Transactional
    public EventSummaryDTO update(Long id, EventUpdateDTO updateDTO) {
        if (id == null || !eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found for update id: " + id);
        } // <--- Aquí cerramos la llave del IF correctamente y liberamos el resto de la lógica

        Event event = eventMapper.toEntity(updateDTO);
        event.setId(id);

        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toSummaryDTO(updatedEvent);
    }

    @Transactional(readOnly = true)
    public EventSummaryDTO findByid(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with this id : " + id));
        return eventMapper.toSummaryDTO(event);
    }
}