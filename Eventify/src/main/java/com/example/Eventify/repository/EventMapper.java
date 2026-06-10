package com.example.Eventify.repository;

import com.example.Eventify.dto.EventCreateDTO;
import com.example.Eventify.dto.EventResponseDTO;
import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.dto.EventUpdateDTO;
import com.example.Eventify.entities.Category;
import com.example.Eventify.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, Category.class})
public interface EventMapper {

    @Mapping(source = "venue.places", target = "venuename")
    @Mapping(source = "categories", target = "categorynames", qualifiedByName = "mapCategoriesToNames")
    EventResponseDTO toResponseDTO(Event event);

    @Mapping(source = "name", target = "nameevent")
    @Mapping(source = "venue.places", target = "nameplace")
    @Mapping(source = "venue.city", target = "city")
    EventSummaryDTO toSummaryDTO(Event event);

    @Named("mapCategoriesToNames")
    default String mapCategoriesToNames(Set<Category> categories) {
        if (categories == null) return null;
        return categories.stream().map(Category::getName).collect(Collectors.joining(", "));
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Event toEntity(EventCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Event toEntity(EventUpdateDTO dto);
}