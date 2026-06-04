package com.example.Eventify.dto;

import com.example.Eventify.entities.Category;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

public record EventSummaryDTO(
        String nameevent,
        String date,
        Long venueid,
        String nameplace,
        String city
) {}
