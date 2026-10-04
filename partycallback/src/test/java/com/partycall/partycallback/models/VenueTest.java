package com.partycall.partycallback.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VenueTest {

    Venue venue;
    Event event1;
    Event event2;
    List<Event> eventList;

    @BeforeEach
    public void setUp(){
        venue = new Venue();
        event1 = new Event();
        event2 = new Event();

        eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
    }

    @Test
    public void testGetterAndSetters(){
        venue.setVenueId(998);
        venue.setName("Madison Square Garden");
        venue.setAddress("1 NYC Dr");
        venue.setCity("New York City");
        venue.setState("NY");
        venue.setZipCode("12345");
        venue.setEvents(eventList);

        assertEquals(venue.getVenueId(), 998);
        assertEquals(venue.getName(), "Madison Square Garden");
        assertEquals(venue.getAddress(), "1 NYC Dr");
        assertEquals(venue.getCity(), "New York City");
        assertEquals(venue.getState(), "NY");
        assertEquals(venue.getZipCode(), "12345");
        assertEquals(venue.getEvents(), eventList);

    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(venue);
    }



    
}
