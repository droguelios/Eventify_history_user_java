package com.example.Eventify.dto;

import lombok.NonNull;

public record EventSummaryDTO(
        String nameevent,
        String date,
        String nameplace,
        String city
) {}
