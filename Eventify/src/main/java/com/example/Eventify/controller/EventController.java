package com.example.Eventify.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.Eventify.entities.Event;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.EventService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Events", description = "API para gestion de eventos")
@RequestMapping("/events")
@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> findAllAll() {
        return eventService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Event event) {

        eventService.insert(event);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping
    public void delete(@RequestBody Event event) {
        eventService.delete(event);
    }

    @PutMapping
    public void update(@RequestBody Event event) {
        eventService.update(event);
    }

    @GetMapping("/{id}")
    public Event findByid(@PathVariable long id) {
        return eventService.findByid(id);
    }
}
