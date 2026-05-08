package com.example.Eventify.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private String id;
    private String places;
    private String city;
    private String capacity;
}
