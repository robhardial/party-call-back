package com.partycall.partycallback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.services.EventService;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    @Autowired
    EventService eventService;

    /**
     * Retrieves all events from the database.
     *
     * @return A ResponseEntity containing a list of Event objects representing all
     *         events in the database.
     */
    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        List<Event> events = eventService.findAllEvents();
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    /**
     * Retrieves a Event from the database based on the provided ID.
     *
     * @param id the ID of the Event to retrieve
     * @return a ResponseEntity containing the requested Event if found, or
     *         HttpStatus.OK if successful
     */
    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Event event = eventService.findEventById(id);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    /**
     * Creates a new event with the provided event information.
     *
     * @param event The event object containing the event information.
     * @return A ResponseEntity object with the created event and HTTP status code
     *         201 (Created).
     */
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.saveEvent(event);
        return new ResponseEntity<Event>(newEvent, HttpStatus.CREATED);
    }

    /**
     * Edits an existing event in the system with the specified ID.
     *
     * @param id    The ID of the event to edit.
     * @param event The updated event object with the new email and password.
     * @return The edited event object.
     */
    @PutMapping("/event/{id}")
    public ResponseEntity<Event> editEvent(@PathVariable int id, @RequestBody Event event) {
        Event updatedEvent = eventService.editEvent(id, event);
        return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
    }

    /**
     * Deletes a event by their ID.
     *
     * @param id the ID of the event to be deleted
     * @return a response entity indicating success with no content
     */
    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable int id) {
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

}
