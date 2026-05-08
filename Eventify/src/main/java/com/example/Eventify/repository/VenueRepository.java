package com.example.Eventify.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.Eventify.entities.Venue;
import org.springframework.stereotype.Repository;

@Repository
public class VenueRepository {
    private final List<Venue> venues = new ArrayList<>();

    public List<Venue> findAll() {
        return venues;
    }

    public void save(Venue venue) {
        venues.add(venue);
    }
}
