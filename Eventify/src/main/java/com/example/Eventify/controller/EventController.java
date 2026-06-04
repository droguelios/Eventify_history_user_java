package com.example.Eventify.controller;

import com.example.Eventify.dto.EventSummaryDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/events")
@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(
            summary = "Search events with advanced filters",
            description = "Retrieves a paginated Slice of active event summaries based on city and category. Inactive or soft-deleted events are automatically excluded."
    )
    @GetMapping("/search")
    public ResponseEntity<Slice<EventSummaryDTO>> searchEvents(
            @Parameter(description = "Partial or full name of the city (case-insensitive)")
            @RequestParam(required = false) String city,

            @Parameter(description = "Name of the category (case-insensitive)")
            @RequestParam(required = false) String category,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Slice<EventSummaryDTO> results = eventService.searchEvents(city, category, page, size);
        return ResponseEntity.ok(results);
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

    @Operation(summary = "Eliminar evento por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
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
