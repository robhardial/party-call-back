package com.partycall.partycallback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partycall.partycallback.models.Venue;
import com.partycall.partycallback.repositiories.VenueRepository;

@Service
public class VenueService {

    @Autowired
    VenueRepository venueRepository;

    public List<Venue> findAllVenues() {
        return venueRepository.findAll();
    }

    /**
     * Finds a Venue by their ID.
     *
     * @param id the ID of the Venue to find
     * @return the Venue with the specified ID, or null if no Venue is found
     */
    public Venue findVenueById(int id) {
        Optional<Venue> venue = venueRepository.findById(id);

        if (venue.isPresent()) {

            return venue.get();
        }

        return null;
    }

    /**
     * Saves the given venue into the repository.
     *
     * @param venue The venue to be saved.
     * @return The saved venue.
     */
    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    /**
     * Edits an existing venue in the system with the specified ID.
     *
     * @param id    The ID of the venue to edit.
     * @param venue The updated venue object with the new email and password.
     * @return The edited venue object.
     */
    public Venue editVenue(int id, Venue venue) {
        Optional<Venue> existingVenueOptional = venueRepository.findById(id);

        if (existingVenueOptional.isPresent()) {

            Venue existingVenue = existingVenueOptional.get();

            return venueRepository.save(existingVenue);
        } else {
            return venueRepository.save(venue);
        }
    }

    /**
     * Deletes a venue by their ID.
     *
     * @param id the ID of the venue to be deleted
     */
    public void deleteVenueById(int id) {
        venueRepository.deleteById(id);
    }

}
