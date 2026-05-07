package com.example.Eventify.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Eventify.entities.Event;
import com.example.Eventify.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void insert(Event event) {
        eventRepository.save(event);
    }
}
