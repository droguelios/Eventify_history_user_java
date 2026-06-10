package com.example.Eventify.controller;

import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventUIController.class)
public class EventUIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventService eventService;

    @Test
    public void testEventList_ReturnsViewWithEvents() throws Exception {
        EventSummaryDTO event1 = new EventSummaryDTO(1L, "Evento 1", "2026-09-10", "Lugar 1", "Ciudad 1");
        EventSummaryDTO event2 = new EventSummaryDTO(2L, "Evento 2", "2026-09-11", "Lugar 2", "Ciudad 2");

        List<EventSummaryDTO> events = Arrays.asList(event1, event2);

        when(eventService.findAll()).thenReturn(events);
        mockMvc.perform(get("/admin/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/index"))
                .andExpect(model().attributeExists("events"))
                .andExpect(model().attributeExists("tituloPantalla"))
                .andExpect(model().attribute("tituloPantalla", "Administración de Eventos - Eventify"));
    }

    @Test
    public void testEventList_EmptyList() throws Exception {
        List<EventSummaryDTO> emptyEvents = List.of();
        when(eventService.findAll()).thenReturn(emptyEvents);

        mockMvc.perform(get("/admin/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/index"))
                .andExpect(model().attributeExists("events"))
                .andExpect(model().attribute("events", emptyEvents));
    }

    @Test
    public void testShowCreateForm_ReturnsViewWithNewEvent() throws Exception {
        mockMvc.perform(get("/admin/events/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/create"))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    public void testCreateEvent_RedirectsToEventList() throws Exception {
        mockMvc.perform(post("/admin/events/new")
                        .param("name", "Nuevo Evento")
                        .param("date", "2024-03-01")
                        .param("description", "Nueva descripción"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/events"));
    }
}
