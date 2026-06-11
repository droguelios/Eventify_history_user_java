package com.example.Eventify.dto;

public record EventResponseDTO(
        Long id,
        String name,
        String date,
        String description,
        String venuename,
        String categorynames
) {}