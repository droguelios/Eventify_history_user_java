package com.example.Eventify.repository;

import com.example.Eventify.dto.EventSummaryDTO;
import com.example.Eventify.entities.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
        SELECT new com.example.Eventify.dto.EventSummaryDTO(
            e.id,
            e.name, 
            e.date, 
            v.places, 
            v.city
        ) 
        FROM Event e 
        JOIN e.venue v 
        LEFT JOIN e.categories c
        WHERE (:city IS NULL OR LOWER(v.city) LIKE LOWER(CONCAT('%', :city, '%')))
          AND (:category IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :category, '%')))
        ORDER BY e.date DESC
    """)
    Slice<EventSummaryDTO> findEventsWithFilters(
            @Param("city") String city,
            @Param("category") String category,
            Pageable pageable
    );
}