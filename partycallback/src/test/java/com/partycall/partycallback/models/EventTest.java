package com.partycall.partycallback.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {

    private Event event;
    private Venue venue;
    private Category category;
    private User creator;
    private Ticket ticket1;
    private Ticket ticket2;
    private List<Ticket> ticketList;

    @BeforeEach
    public void setUp() {
        event = new Event();
        venue = new Venue();
        category = new Category();
        creator = new User();
        ticket1 = new Ticket();
        ticket2 = new Ticket();
        ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
    }

    @Test
    public void testGettersAndSetters() {
        // Set event fields
        event.setEventId(101);
        event.setTitle("Tech Conference");
        event.setVenue(venue);
        event.setPrice(BigDecimal.valueOf(49.99));
        event.setDescription("A technology conference for developers.");
        event.setStartDate(LocalDate.of(2025, 1, 15));
        event.setEndDate(LocalDate.of(2025, 1, 16));
        event.setStartTime(LocalTime.of(9, 0));
        event.setEndTime(LocalTime.of(17, 0));
        event.setImageUrl("http://example.com/image.jpg");
        event.setQuanTickets(150);
        event.setCategory(category);
        event.setCreator(creator);
        event.setTickets(ticketList);

        // Verify fields
        assertEquals(101, event.getEventId());
        assertEquals("Tech Conference", event.getTitle());
        assertEquals(venue, event.getVenue());
        assertEquals(BigDecimal.valueOf(49.99), event.getPrice());
        assertEquals("A technology conference for developers.", event.getDescription());
        assertEquals(LocalDate.of(2025, 1, 15), event.getStartDate());
        assertEquals(LocalDate.of(2025, 1, 16), event.getEndDate());
        assertEquals(LocalTime.of(9, 0), event.getStartTime());
        assertEquals(LocalTime.of(17, 0), event.getEndTime());
        assertEquals("http://example.com/image.jpg", event.getImageUrl());
        assertEquals(150, event.getQuanTickets());
        assertEquals(category, event.getCategory());
        assertEquals(creator, event.getCreator());
        assertEquals(ticketList, event.getTickets());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(event);
    }

    @Test
    public void testSetAndGetTitle() {
        event.setTitle("Networking Event");
        assertEquals("Networking Event", event.getTitle());
    }

    @Test
    public void testSetAndGetVenue() {
        event.setVenue(venue);
        assertEquals(venue, event.getVenue());
    }

    @Test
    public void testSetAndGetCreator() {
        event.setCreator(creator);
        assertEquals(creator, event.getCreator());
    }

    @Test
    public void testSetAndGetTickets() {
        event.setTickets(ticketList);
        assertEquals(2, event.getTickets().size());
        assertEquals(ticket1, event.getTickets().get(0));
        assertEquals(ticket2, event.getTickets().get(1));
    }
    
}
