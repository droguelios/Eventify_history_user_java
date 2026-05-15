package com.example.Eventify.controller;

import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.VenueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;

@Tag(name = "Venues", description = "API para gestion de espacios")
@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @Operation(summary = "listar venues con paginacion y ordenamiento")
    @ApiResponse(responseCode = "200", description = "Venues encontrados")
    @GetMapping
    public Page<Venue> findAll(@ParameterObject Pageable pageable) {
        return venueService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insert(@RequestBody Venue venue) {
        venueService.insert(venue);
    }

    @Operation(summary = "Eliminar venue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@RequestBody Venue venue) {
        venueService.delete(venue);
    }

    @PutMapping
    public void update(@RequestBody Venue venue) {
        venueService.update(venue);
    }

    @Operation(summary = "obtener venue por id")
    @ApiResponse(responseCode = "200", description = "Venue encontrado")
    @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    @GetMapping("/{id}")
    public Venue findById(@PathVariable Long id) {
        return venueService.findById(id);
    }
}
