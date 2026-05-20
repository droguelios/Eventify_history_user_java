package com.example.Eventify.controller;

import com.example.Eventify.entities.Venue;
import com.example.Eventify.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/venues")
public class VenueUIController {

    private final VenueService venueService;

    public VenueUIController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public String venueList(Model model) {
        List<Venue> venues = venueService.findAll();
        model.addAttribute("venues", venues);
        model.addAttribute("tituloPantalla", "Catálogo de Sedes - Eventify");
        return "venues/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "venues/create";
    }

    @PostMapping("/new")
    public String createVenue(@ModelAttribute Venue venue) {
        venueService.insert(venue);
        return "redirect:/admin/venues";
    }
}
