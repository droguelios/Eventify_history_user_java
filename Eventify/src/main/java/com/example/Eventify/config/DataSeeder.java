package com.example.Eventify.config;

import com.example.Eventify.dto.EventCreateDTO;
import com.example.Eventify.dto.EventResponseDTO;
import com.example.Eventify.dto.VenueCreateDTO;
import com.example.Eventify.dto.VenueResponseDTO;
import com.example.Eventify.service.EventService;
import com.example.Eventify.service.VenueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {
    private final EventService eventService;
    private final VenueService venueService;

    // Usamos el constructor limpio para inyección de Spring
    DataSeeder(EventService eventService, VenueService venueService) {
        this.eventService = eventService;
        this.venueService = venueService;
    }

    @Bean
    public CommandLineRunner seedData() {
        return args -> {

            VenueCreateDTO venueDTO = new VenueCreateDTO("Estadio Nacional", "México", 12000);


            VenueResponseDTO savedVenue = venueService.insert(venueDTO);


            EventCreateDTO eventDTO = new EventCreateDTO(
                    "Concierto Rock",
                    "2026-09-08",
                    "Concierto de rock",
                    savedVenue.id()
            );

            // Insertamos el evento usando el DTO
            eventService.insert(eventDTO);
        };
    }
}