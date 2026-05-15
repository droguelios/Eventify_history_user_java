package com.example.Eventify.controller;

import java.util.List;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.VenueService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

@Tag(name = "Venues", description = "API para gestion de espacios")
@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public List<Venue> findAll() {
        return venueService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insert(@RequestBody Venue venue) {
        venueService.insert(venue);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping
    public void delete(@RequestBody Venue venue) {
        venueService.delete(venue);
    }

    @PutMapping
    public void update(@RequestBody Venue venue) {
        venueService.update(venue);
    }

    @GetMapping("/{id}")
    public Venue findById(@PathVariable Long id) {
        return venueService.findById(id);
    }
}
