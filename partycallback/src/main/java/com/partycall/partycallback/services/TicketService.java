package com.partycall.partycallback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partycall.partycallback.models.Ticket;
import com.partycall.partycallback.repositiories.TicketRepository;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Finds a ticket by their ID.
     *
     * @param id the ID of the ticket to find
     * @return the ticket with the specified ID, or null if no ticket is found
     */
    public Ticket findTicketById(int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);

        if (ticket.isPresent()) {

            return ticket.get();
        }

        return null;
    }

    /**
     * Saves the given ticket into the repository.
     *
     * @param ticket The ticket to be saved.
     * @return The saved ticket.
     */
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Edits an existing ticket in the system with the specified ID.
     *
     * @param id     The ID of the ticket to edit.
     * @param ticket The updated ticket object with the new email and password.
     * @return The edited ticket object.
     */
    public Ticket editTicket(int id, Ticket ticket) {
        Optional<Ticket> existingTicketOptional = ticketRepository.findById(id);

        if (existingTicketOptional.isPresent()) {

            Ticket existingTicket = existingTicketOptional.get();

            if (ticket.getPrice() != null) {
                existingTicket.setPrice(ticket.getPrice());
            }

            if (ticket.getEvent() != null) {
                existingTicket.setEvent(ticket.getEvent());
            }

            if (ticket.getAttendee() != null) {
                existingTicket.setAttendee(ticket.getAttendee());
            }

            return ticketRepository.save(existingTicket);
        } else {
            return ticketRepository.save(ticket);
        }
    }

    /**
     * Deletes a ticket by their ID.
     *
     * @param id the ID of the ticket to be deleted
     */
    public void deleteTicketById(int id) {
        ticketRepository.deleteById(id);
    }

}
