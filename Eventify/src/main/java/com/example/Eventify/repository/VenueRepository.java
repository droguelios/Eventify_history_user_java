package com.example.Eventify.repository;

import com.example.Eventify.dto.VenuetSummaryDTO;
import com.example.Eventify.entities.Venue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

    @Query("""
        SELECT new com.example.Eventify.dto.VenuetSummaryDTO(
            v.id, 
            v.places, 
            v.city, 
            v.capacity
        ) 
        FROM Venue v
        WHERE (:city IS NULL OR LOWER(v.city) LIKE LOWER(CONCAT('%', :city, '%')))
          AND (:name IS NULL OR LOWER(v.places) LIKE LOWER(CONCAT('%', :name, '%')))
        ORDER BY v.places ASC
    """)
    Slice<VenuetSummaryDTO> findVenuesWithFilters(
            @Param("city") String city,
            @Param("name") String name,
            Pageable pageable
    );
}