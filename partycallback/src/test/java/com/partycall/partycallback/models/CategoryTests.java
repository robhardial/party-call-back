package com.partycall.partycallback.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTests {

    private Category category;
    private Event event1;
    private Event event2;
    List<Event> eventList;

    @BeforeEach
    public void setUp() {
        category = new Category();
        event1 = new Event();
        event2 = new Event();
        eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
    }

    @Test
    public void testGettersAndSetters(){
        category.setCategoryId(998);
        category.setName("Networking");
        category.setEvents(eventList);

        assertEquals(category.getCategoryId(), 998);
        assertEquals(category.getName(), "Networking");
        assertEquals(category.getEvents(), eventList);

    }
    
}
