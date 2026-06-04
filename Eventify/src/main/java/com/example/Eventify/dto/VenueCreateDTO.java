package com.example.Eventify.dto;

import com.example.Eventify.entities.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record VenueCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String city,
        @NotNull(message = "Esto no puede estar vacio")
        @Min(value = 1 , message = "La capacidad debe ser mayor a 0")
        Integer capacity,
        @NotEmpty
        Set<Category> categories

) {
}
