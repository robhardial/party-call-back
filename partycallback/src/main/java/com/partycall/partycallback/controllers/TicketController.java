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

import com.partycall.partycallback.models.Ticket;
import com.partycall.partycallback.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    /**
     * Retrieves all tickets from the database.
     *
     * @return A ResponseEntity containing a list of Ticket objects representing all
     *         tickets in the database.
     */
    @GetMapping
    public ResponseEntity<List<Ticket>> findAllTickets() {
        List<Ticket> tickets = ticketService.findAllTickets();
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    /**
     * Retrieves a Ticket from the database based on the provided ID.
     *
     * @param id the ID of the Ticket to retrieve
     * @return a ResponseEntity containing the requested Ticket if found, or
     *         HttpStatus.OK if successful
     */
    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id) {
        Ticket ticket = ticketService.findTicketById(id);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    /**
     * Creates a new ticket with the provided ticket information.
     *
     * @param ticket The ticket object containing the ticket information.
     * @return A ResponseEntity object with the created ticket and HTTP status code
     *         201 (Created).
     */
    @PostMapping("/ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.saveTicket(ticket);
        return new ResponseEntity<Ticket>(newTicket, HttpStatus.CREATED);
    }

    /**
     * Edits an existing ticket in the system with the specified ID.
     *
     * @param id     The ID of the ticket to edit.
     * @param ticket The updated ticket object with the new email and password.
     * @return The edited ticket object.
     */
    @PutMapping("/ticket/{id}")
    public ResponseEntity<Ticket> editTicket(@PathVariable int id, @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.editTicket(id, ticket);
        return new ResponseEntity<Ticket>(updatedTicket, HttpStatus.OK);
    }

    /**
     * Deletes a ticket by their ID.
     *
     * @param id the ID of the ticket to be deleted
     * @return a response entity indicating success with no content
     */
    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable int id) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.noContent().build();
    }

}
