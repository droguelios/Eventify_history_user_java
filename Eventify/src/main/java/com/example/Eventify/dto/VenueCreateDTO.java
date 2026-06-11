package com.example.Eventify.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VenueCreateDTO(
        @NotBlank String places,
        @NotBlank String city,
        @NotNull @Min(value = 1) Integer capacity
) {}