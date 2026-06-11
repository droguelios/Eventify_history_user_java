package com.example.Eventify.dto;

import com.example.Eventify.entities.Category;

import java.util.Set;


public record VenueResponseDTO(
        Long id,
        String places,
        String city,
        Integer capacity
) {}
