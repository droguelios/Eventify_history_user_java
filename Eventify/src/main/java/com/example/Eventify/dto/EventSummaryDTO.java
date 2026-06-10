package com.example.Eventify.dto;




public record EventSummaryDTO(
        Long id,
        String nameevent,
        String date,
        String nameplace,
        String city
) {}
