package com.example.Eventify.controller;

import com.example.Eventify.dto.VenueCreateDTO;
import com.example.Eventify.dto.VenueResponseDTO;
import com.example.Eventify.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

@Tag(name = "Venues", description = "API para gestion de espacios")
@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @Operation(summary = "listar venues con paginacion y ordenamiento")
    @ApiResponse(responseCode = "200", description = "Venues encontrados")
    @GetMapping
    public Page<VenueResponseDTO> findAll(@ParameterObject Pageable pageable) {
        return venueService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VenueResponseDTO insert(@Valid @RequestBody VenueCreateDTO dto) {
        return venueService.insert(dto);
    }

    @Operation(summary = "Eliminar venue por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        venueService.delete(id);
    }

    @PutMapping("/{id}")
    public VenueResponseDTO update(@PathVariable Long id, @Valid @RequestBody VenueCreateDTO dto) {
        return venueService.update(id, dto);
    }

    @Operation(summary = "obtener venue por id")
    @ApiResponse(responseCode = "200", description = "Venue encontrado")
    @ApiResponse(responseCode = "404", description = "Venue no encontrado")
    @GetMapping("/{id}")
    public VenueResponseDTO findById(@PathVariable Long id) {
        return venueService.findById(id);
    }
}