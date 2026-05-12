package com.example.Eventify.config;

import com.example.Eventify.service.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.Eventify.entities.Event;

@Configuration
public class DataSeeder {
    private final EventService eventService;

    DataSeeder(EventService eventService) {
        this.eventService = eventService;
    }

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            // Pasamos null en el ID porque es autoincremental
            eventService.insert(new Event(null, "Concierto Rock", "2026-09-08", "Evento de rock en vivo"));
        };
    }

}
