package com.example.Eventify.controller;

import com.example.Eventify.entities.Category;
import com.example.Eventify.entities.Event;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashSet;
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

        Venue venueDummy = new Venue();
        java.util.Set<Category> categoriasDummy = new java.util.HashSet<>();


        Event event1 = new Event(null, "test JPA", true, "2026-09-10", "Desc", venueDummy, categoriasDummy);
        Event event2 = new Event(null, "test JPA", true, "2026-09-11", "Desc", venueDummy, categoriasDummy);

        // 3. Metemos los eventos en la lista
        List<Event> events = Arrays.asList(event1, event2);

        // 4. El comportamiento del Mock
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
        // Arrange
        List<Event> emptyEvents = List.of();
        when(eventService.findAll()).thenReturn(emptyEvents);

        // Act & Assert
        mockMvc.perform(get("/admin/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/index"))
                .andExpect(model().attributeExists("events"))
                .andExpect(model().attribute("events", emptyEvents));
    }

    @Test
    public void testShowCreateForm_ReturnsViewWithNewEvent() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/admin/events/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/create"))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    public void testCreateEvent_RedirectsToEventList() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/admin/events/new")
                        .param("name", "Nuevo Evento")
                        .param("date", "2024-03-01")
                        .param("description", "Nueva descripción"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/events"));
    }
}
