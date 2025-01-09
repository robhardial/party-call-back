package com.partycall.partycallback.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;
    private Event event1;
    private Event event2;
    private Ticket ticket1;
    private Ticket ticket2;
    private List<Event> eventsOrganized;
    private List<Ticket> tickets;

    @BeforeEach
    public void setUp() {
        user = new User();
        event1 = new Event();
        event2 = new Event();
        ticket1 = new Ticket();
        ticket2 = new Ticket();
        
        eventsOrganized = new ArrayList<>();
        eventsOrganized.add(event1);
        eventsOrganized.add(event2);

        tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
    }

    @Test
    public void testGettersAndSetters() {
        user.setUserId(1);
        user.setEmail("testuser@example.com");
        user.setPassword("securepassword");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEventsOrganized(eventsOrganized);
        user.setTickets(tickets);
        user.setRole(Role.USER);


        assertEquals(1, user.getUserId());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("securepassword", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(eventsOrganized, user.getEventsOrganized());
        assertEquals(tickets, user.getTickets());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(user);
    }

    @Test
    public void testSetAndGetEventsOrganized() {
        user.setEventsOrganized(eventsOrganized);
        assertEquals(2, user.getEventsOrganized().size());
        assertEquals(event1, user.getEventsOrganized().get(0));
        assertEquals(event2, user.getEventsOrganized().get(1));
    }

    @Test
    public void testSetAndGetTickets() {
        user.setTickets(tickets);
        assertEquals(2, user.getTickets().size());
        assertEquals(ticket1, user.getTickets().get(0));
        assertEquals(ticket2, user.getTickets().get(1));
    }


    
}
