package com.example.Eventify.config;

import com.example.Eventify.entities.Event;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.EventService;
import com.example.Eventify.service.VenueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class DataSeeder {
    private final EventService eventService;
    private final VenueService venueService;

    DataSeeder(EventService eventService, VenueService venueService) {
        this.eventService = eventService;
        this.venueService = venueService;
    }

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            Venue venue = new Venue(null, "Estadio Nacional", "México", 12000);
            venueService.insert(venue);

            eventService.insert(new Event(
                    null,
                    "Concierto Rock",
                    true,
                    "2026-09-08",
                    "Evento de rock en vivo",
                    venue,
                    new HashSet<>()
            ));
        };
    }

}
