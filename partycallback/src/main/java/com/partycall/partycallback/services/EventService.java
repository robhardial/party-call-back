package com.partycall.partycallback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.models.Venue;
import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.repositiories.EventRepository;
import com.partycall.partycallback.repositiories.VenueRepository;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    VenueRepository venueRepository;

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Finds a Event by their ID.
     *
     * @param id the ID of the Event to find
     * @return the Event with the specified ID, or null if no Event is found
     */
    public Event findEventById(int id) {
        Optional<Event> event = eventRepository.findById(id);

        if (event.isPresent()) {

            return event.get();
        }

        return null;
    }

    /**
     * Saves the given Event into the repository.
     *
     * @param Event The Event to be saved.
     * @return The saved Event.
     */
    public Event saveEvent(Event event) {

        if (event.getVenue() != null) {
            Optional<Venue> existingVenue = venueRepository.findByName(event.getVenue().getName());
    
            // Check if the venue is present
            if (existingVenue.isPresent()) {
                // If the venue exists, use it
                event.setVenue(existingVenue.get());
            } else {
                // If the venue doesn't exist, save the new venue and set it to the event
                Venue savedVenue = venueRepository.save(event.getVenue());
                event.setVenue(savedVenue);
            }
        }
    
        // Save the event
        return eventRepository.save(event);
    }
    

    /**
     * Edits an existing Event in the system with the specified ID.
     *
     * @param id    The ID of the Event to edit.
     * @param Event The updated Event object with the new email and password.
     * @return The edited Event object.
     */
    public Event editEvent(int id, Event event) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);

        if (existingEventOptional.isPresent()) {
            Event existingEvent = existingEventOptional.get();

            if (event.getCategory() != null) {
                existingEvent.setCategory(event.getCategory());
            }
            if (event.getDescription() != null) {
                existingEvent.setDescription(event.getDescription());
            }
            if (event.getStartDate() != null) {
                existingEvent.setStartDate(event.getStartDate());
            }
            if (event.getEndDate() != null) {
                existingEvent.setEndDate(event.getEndDate());
            }
            if (event.getStartTime() != null) {
                existingEvent.setStartTime(event.getStartTime());
            }
            if (event.getEndTime() != null) {
                existingEvent.setEndTime(event.getEndTime());
            }
            if (event.getVenue() != null) {
                existingEvent.setVenue(event.getVenue());
            }
            if (event.getTitle() != null) {
                existingEvent.setTitle(event.getTitle());
            }
            if (event.getQuanTickets() != 0) {
                existingEvent.setQuanTickets(event.getQuanTickets());
            }

            return eventRepository.save(existingEvent);

        } else {
            return eventRepository.save(event);
        }
    }

    /**
     * Deletes a Event by their ID.
     *
     * @param id the ID of the Event to be deleted
     */
    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }

}
