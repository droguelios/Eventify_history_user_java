package com.example.Eventify.controller;

import java.util.List;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    
    private final VenueService venueService;

    @GetMapping
    public List<Venue> findAll() {
        return venueService.findAll();
    }

    @PostMapping
    public void insert(@RequestBody Venue venue) {
        venueService.insert(venue);
    }
}
