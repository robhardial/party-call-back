package com.partycall.partycallback.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {

    Ticket ticket;
    Event event;

    @BeforeEach
    public void setUp(){
        ticket = new Ticket();
        event = new Event();
    }

    @Test
    public void testGettersAndSetters(){
        ticket.setTicketId(998);
        ticket.setPrice(BigDecimal.valueOf(49.99));
        ticket.setEvent(event);

        assertEquals(ticket.getTicketId(), 998);
        assertEquals(ticket.getPrice(), BigDecimal.valueOf(49.99));
        assertEquals(ticket.getEvent(), event);
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(ticket);
    }
    
}
