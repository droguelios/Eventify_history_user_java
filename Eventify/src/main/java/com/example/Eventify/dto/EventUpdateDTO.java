package com.example.Eventify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventUpdateDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String date,
        String description,
        Long Venueid
) {
}
