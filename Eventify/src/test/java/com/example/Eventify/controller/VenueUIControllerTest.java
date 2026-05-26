package com.example.Eventify.controller;

import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.VenueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VenueUIController.class)
public class VenueUIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VenueService venueService;

    @Test
    public void testVenueList_ReturnsViewWithVenues() throws Exception {
        // Arrange
        Venue venue1 = new Venue(1L, "Lugar 1", "Ciudad 1", 100);
        Venue venue2 = new Venue(2L, "Lugar 2", "Ciudad 2", 200);
        List<Venue> venues = Arrays.asList(venue1, venue2);
        
        when(venueService.findAll()).thenReturn(venues);

        // Act & Assert
        mockMvc.perform(get("/admin/venues"))
                .andExpect(status().isOk())
                .andExpect(view().name("venues/index"))
                .andExpect(model().attributeExists("venues"))
                .andExpect(model().attributeExists("tituloPantalla"))
                .andExpect(model().attribute("tituloPantalla", "Catálogo de Sedes - Eventify"));
    }

    @Test
    public void testVenueList_EmptyList() throws Exception {
        // Arrange
        List<Venue> emptyVenues = List.of();
        when(venueService.findAll()).thenReturn(emptyVenues);

        // Act & Assert
        mockMvc.perform(get("/admin/venues"))
                .andExpect(status().isOk())
                .andExpect(view().name("venues/index"))
                .andExpect(model().attributeExists("venues"))
                .andExpect(model().attribute("venues", emptyVenues));
    }

    @Test
    public void testShowCreateForm_ReturnsViewWithNewVenue() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/admin/venues/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("venues/create"))
                .andExpect(model().attributeExists("venue"));
    }

    @Test
    public void testCreateVenue_RedirectsToVenueList() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/admin/venues/new")
                        .param("places", "Nuevo Lugar")
                        .param("city", "Nueva Ciudad")
                        .param("capacity", "150"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/venues"));
    }
}
