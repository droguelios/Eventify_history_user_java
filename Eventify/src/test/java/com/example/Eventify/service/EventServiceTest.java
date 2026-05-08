package com.example.Eventify.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Eventify.entities.Event;
import com.example.Eventify.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void testFindAll() {

        when(eventRepository.findAll()).thenReturn(Collections.emptyList());
        List<Event> result = eventService.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(eventRepository, times(1)).findAll();

    }
}
