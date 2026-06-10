package com.example.Eventify.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Eventify.entities.Category;
import com.example.Eventify.entities.Event;
import com.example.Eventify.entities.Venue;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testSaveAndFindById() {
        Venue venue = new Venue(null, "Lugar", "Ciudad", 100, new HashSet<>());
        Event event = new Event(null, "test JPA", true, "2026-05-15", "Desc", venue, new HashSet<>());
        Event saved = eventRepository.save(event);

        assertNotNull(saved.getId());
        assertEquals("test JPA", saved.getName());
    }
}
