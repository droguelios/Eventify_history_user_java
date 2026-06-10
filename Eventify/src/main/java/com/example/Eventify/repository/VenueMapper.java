package com.example.Eventify.repository;

import com.example.Eventify.dto.VenueCreateDTO;
import com.example.Eventify.dto.VenueResponseDTO;
import com.example.Eventify.entities.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    VenueResponseDTO toResponseDTO(Venue venue);

    List<VenueResponseDTO> toResponseDTOList(List<Venue> venues);

    @Mapping(target = "id", ignore = true)
    Venue toEntity(VenueCreateDTO dto);
}