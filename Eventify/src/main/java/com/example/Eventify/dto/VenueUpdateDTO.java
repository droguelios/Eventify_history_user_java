package com.example.Eventify.dto;

import jakarta.validation.constraints.NotBlank;

public record VenueUpdateDTO(
        @NotBlank String name,
        @NotBlank String date,
        String description,
        Long venueId
) {
}
