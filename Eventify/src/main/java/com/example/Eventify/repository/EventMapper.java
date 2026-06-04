package com.example.Eventify.repository;

import com.example.Eventify.dto.EventCreateDTO;
import com.example.Eventify.dto.EventResponseDTO;
import com.example.Eventify.entities.Category;
import com.example.Eventify.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, Category.class})
public interface EventMapper {

    // ========== Event → EventResponseDTO ==========
    @Mapping(source = "venue.places", target = "venuename")
    @Mapping(source = "categories", target = "categorynames", qualifiedByName = "mapCategoriesToNames")
    EventResponseDTO toResponseDTO(Event event);

    // Convierte Set<Category> a un String con los nombres separados por coma
    @Named("mapCategoriesToNames")
    default String mapCategoriesToNames(Set<Category> categories) {
        if (categories == null) return null;
        return categories.stream().map(Category::getName).collect(Collectors.joining(", "));
    }

    // ========== EventCreateDTO → Event ==========
    @Mapping(target = "id", ignore = true)         // El ID lo genera la DB
    @Mapping(target = "active", constant = "true") // Por defecto activo
    @Mapping(target = "venue", ignore = true)      // Se asigna en el Service
    @Mapping(target = "categories", ignore = true) // Se asigna en el Service
    @Mapping(source = "nameevent", target = "name") // nameevent → name
    Event toEntity(EventCreateDTO dto);
}