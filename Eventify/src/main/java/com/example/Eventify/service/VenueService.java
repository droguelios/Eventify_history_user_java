package com.example.Eventify.service;

import java.util.List;
import com.example.Eventify.entities.Venue;
import com.example.Eventify.exceptions.ResourceNotFoundException;
import com.example.Eventify.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public List<Venue> findAll() {
        return venueRepository.findAll();
    }

    public void insert(Venue venue) {
        if (venue.getCapacity() == null) {
            throw new RuntimeException("The venue capacity cannot be empty");
        }
        venueRepository.save(venue);
    }

    public void delete(Venue venue) {
        venueRepository.delete(venue);
    }

    public void update(Venue venue) {
        if (venue.getId() == null || !venueRepository.existsById(venue.getId())) {
            throw new ResourceNotFoundException("Venue not found for update");
        }
        venueRepository.save(venue);
    }

    public Venue findById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "venue not found with this id : " + id));
    }
}
