package com.example.Eventify.service;

import java.util.List;
import com.example.Eventify.entities.Venue;
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
            throw new RuntimeException("the venue can't be empty");
        }
        venueRepository.save(venue);
    }
}
