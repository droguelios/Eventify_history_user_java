package com.example.Eventify.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.entities.Event;
import com.example.Eventify.repository.EventMapper;
import com.example.Eventify.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventService eventService;

    @Test
    void testFindAll() {
        when(eventRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        Pageable pageable = PageRequest.of(0, 10);
        Page<EventSummaryDTO> result = eventService.findAll(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(eventRepository, times(1)).findAll(pageable);
    }
}
