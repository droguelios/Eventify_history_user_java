package com.example.Eventify.service;

import com.example.Eventify.dto.VenueCreateDTO;
import com.example.Eventify.dto.VenueResponseDTO;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.exceptions.ResourceNotFoundException;
import com.example.Eventify.repository.VenueMapper;
import com.example.Eventify.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    public Page<VenueResponseDTO> findAll(Pageable pageable) {
        return venueRepository.findAll(pageable)
                .map(venueMapper::toResponseDTO);
    }

    public List<VenueResponseDTO> findAll() {
        return venueRepository.findAll().stream()
                .map(venueMapper::toResponseDTO)
                .toList();
    }

    public VenueResponseDTO insert(VenueCreateDTO dto) {
        Venue venue = venueMapper.toEntity(dto);
        Venue saved = venueRepository.save(venue);
        return venueMapper.toResponseDTO(saved);
    }

    public void delete(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venue not found for delete id: " + id);
        }
        venueRepository.deleteById(id);
    }

    public VenueResponseDTO update(Long id, VenueCreateDTO dto) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found: " + id));

        venue.setPlaces(dto.places());
        venue.setCity(dto.city());
        venue.setCapacity(dto.capacity());

        Venue saved = venueRepository.save(venue);
        return venueMapper.toResponseDTO(saved);
    }

    public VenueResponseDTO findById(Long id) {
        return venueRepository.findById(id)
                .map(venueMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found: " + id));
    }
}