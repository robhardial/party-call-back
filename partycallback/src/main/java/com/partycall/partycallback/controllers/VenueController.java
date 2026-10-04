package com.partycall.partycallback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partycall.partycallback.models.Venue;
import com.partycall.partycallback.services.VenueService;

@RestController
@RequestMapping("/venues")
public class VenueController {

    @Autowired
    VenueService venueService;

    /**
     * Retrieves all venues from the database.
     *
     * @return A ResponseEntity containing a list of Venue objects representing all
     *         venues in the database.
     */
    @GetMapping
    public ResponseEntity<List<Venue>> findAllVenues() {
        List<Venue> venues = venueService.findAllVenues();
        return new ResponseEntity<List<Venue>>(venues, HttpStatus.OK);
    }

    /**
     * Retrieves a Venue from the database based on the provided ID.
     *
     * @param id the ID of the Venue to retrieve
     * @return a ResponseEntity containing the requested Venue if found, or
     *         HttpStatus.OK if successful
     */
    @GetMapping("/venue/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable int id) {
        Venue venue = venueService.findVenueById(id);
        return new ResponseEntity<Venue>(venue, HttpStatus.OK);
    }

    /**
     * Creates a new venue with the provided venue information.
     *
     * @param venue The venue object containing the venue information.
     * @return A ResponseEntity object with the created venue and HTTP status code
     *         201 (Created).
     */
    @PostMapping("/venue")
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        Venue newVenue = venueService.saveVenue(venue);
        return new ResponseEntity<Venue>(newVenue, HttpStatus.CREATED);
    }

    /**
     * Edits an existing venue in the system with the specified ID.
     *
     * @param id    The ID of the venue to edit.
     * @param venue The updated venue object with the new email and password.
     * @return The edited venue object.
     */
    @PutMapping("/venue/{id}")
    public ResponseEntity<Venue> editVenue(@PathVariable int id, @RequestBody Venue venue) {
        Venue updatedVenue = venueService.editVenue(id, venue);
        return new ResponseEntity<Venue>(updatedVenue, HttpStatus.OK);
    }

    /**
     * Deletes a venue by their ID.
     *
     * @param id the ID of the venue to be deleted
     * @return a response entity indicating success with no content
     */
    @DeleteMapping("/venue/{id}")
    public ResponseEntity<Venue> deleteVenue(@PathVariable int id) {
        venueService.deleteVenueById(id);
        return ResponseEntity.noContent().build();
    }

}
