package com.example.Eventify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventCreateDTO(
        @NotBlank
        String name,

        @NotBlank
        String date,
        @NotNull
        String description,
        @NotNull
        Long venueId
) {}