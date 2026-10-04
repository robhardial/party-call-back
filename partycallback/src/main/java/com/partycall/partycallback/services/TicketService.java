package com.partycall.partycallback.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partycall.partycallback.models.User;
import com.partycall.partycallback.dto.TicketDTO;
import com.partycall.partycallback.dto.UserDTO;
import com.partycall.partycallback.dto.UserTicketsDTO;
import com.partycall.partycallback.dto.UserTicketsEventsDTO;
import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.models.Ticket;
import com.partycall.partycallback.repositiories.TicketRepository;
import com.partycall.partycallback.services.EventService;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

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
    public Ticket saveTicket(TicketDTO ticket) {

        Event event = eventService.getEventById(ticket.getEventId());
        User user = userService.findUserByEmail(ticket.getUserId());

        Ticket newTicket = new Ticket();
        newTicket.setPrice(ticket.getPrice());
        newTicket.setAttendee(user);
        newTicket.setEvent(event);

        return ticketRepository.save(newTicket);
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

    public List<UserTicketsDTO> getTicketsByUserEmail(String email) {

        User user = userService.findUserByEmail(email);
        List<Ticket> tickets = ticketRepository.getTicketsByUserId(user.getUserId());

        return tickets.stream().map(ticket -> {
            UserTicketsDTO dto = new UserTicketsDTO();

            dto.setTicketId(ticket.getTicketId());
            dto.setPrice(ticket.getPrice());

            Event event = ticket.getEvent();

            if(event != null){
                User creator = event.getCreator();

                UserDTO ticketEventCreator = new UserDTO(creator.getFirstName(), creator.getLastName());
                UserTicketsEventsDTO eventsDTO = new UserTicketsEventsDTO();
                eventsDTO.setEventId(event.getEventId());
                eventsDTO.setTitle(event.getTitle());
                eventsDTO.setStartTime(event.getStartTime());
                eventsDTO.setStartDate(event.getStartDate());
                eventsDTO.setVenue(event.getVenue());
                eventsDTO.setPrice(event.getPrice());
                eventsDTO.setImageUrl(event.getImageUrl());
                eventsDTO.setCreator(ticketEventCreator);

                dto.setEvent(eventsDTO);
            }
            return dto;
        }).collect(Collectors.toList());
    }

}
