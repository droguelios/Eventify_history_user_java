package com.example.Eventify.dto;

import com.example.Eventify.entities.Category;

import java.util.Set;

public record VenueResponseDTO(
        long id,
        String name,
        long city,
        long capacity,
        Set <Category> categories
) {
}
