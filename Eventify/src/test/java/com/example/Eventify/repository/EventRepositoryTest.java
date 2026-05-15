package com.example.Eventify.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.Eventify.entities.Event;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testSaveAndFindById() {
        Event event = new Event(null, "test JPA", "2026-05-15", "Desc");
        Event saved = eventRepository.save(event);

        assertNotNull(saved.getId());
        assertEquals("test JPA", saved.getName());
    }
}
