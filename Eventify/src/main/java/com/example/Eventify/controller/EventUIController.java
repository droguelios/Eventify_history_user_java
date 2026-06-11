package com.example.Eventify.controller;

import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.dto.EventCreateDTO;
import com.example.Eventify.dto.EventResponseDTO;
import com.example.Eventify.entities.Event;
import com.example.Eventify.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/events")
public class EventUIController {

    private final EventService eventService;

    public EventUIController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String eventList(Model model) {
        List<EventSummaryDTO> events = eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("tituloPantalla", "Administración de Eventos - Eventify");
        return "events/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("/new")
    public String createEvent(@ModelAttribute EventCreateDTO event) {
        eventService.insert(event);
        return "redirect:/admin/events";
    }
}