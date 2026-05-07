package com.example.Eventify.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.Eventify.entities.Event;

import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {
    private static List<Event> events = new ArrayList<>();

    public List<Event> findAll() {
        return events;
    }

    public void save(Event event) {
        events.add(event);
    }

}
