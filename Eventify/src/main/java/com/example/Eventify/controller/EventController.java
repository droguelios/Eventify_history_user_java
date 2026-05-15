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

import org.springframework.http.HttpStatus;
import com.example.Eventify.entities.Event;
import com.example.Eventify.service.EventService;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Events", description = "API para gestion de eventos")
@RequestMapping("/events")
@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "listar evento con paginacion y ordenamiento")
    @GetMapping
    public Page<Event> findAllAll(@ParameterObject Pageable pageable) {
        return eventService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Event event) {
        eventService.insert(event);
    }

    @Operation(summary = "Eliminar evento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@RequestBody Event event) {
        eventService.delete(event);
    }

    @PutMapping
    public void update(@RequestBody Event event) {
        eventService.update(event);
    }

    @Operation(summary = "obtener evento por id")
    @ApiResponse(responseCode = "200", description = "Evento encontrado")
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @GetMapping("/{id}")
    public Event findByid(@PathVariable long id) {
        return eventService.findByid(id);
    }
}
