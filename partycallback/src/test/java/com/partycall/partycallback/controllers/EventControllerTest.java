package com.partycall.partycallback.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.partycall.partycallback.dto.FileDTO;
import com.partycall.partycallback.dto.SavedFileDTO;
import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.models.User;
import com.partycall.partycallback.services.EventService;
import com.partycall.partycallback.services.FileManagerService;
import com.partycall.partycallback.services.JwtService;
import com.partycall.partycallback.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class) 
public class EventControllerTest {

    @Autowired
    private EventController eventController;

    @MockBean
    private EventService eventService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private FileManagerService fileManager;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    void testFindAllEvents() throws Exception {

        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventService.findAllEvents()).thenReturn(events);

 
        mockMvc.perform(get("/events"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    void testGetEventById() throws Exception {

        Event event = new Event();
        event.setEventId(1);
        when(eventService.findEventById(1)).thenReturn(event);


        mockMvc.perform(get("/events/event/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.eventId").value(1));
    }

    @Test
    void testCreateEvent() throws Exception {

        Event event = new Event();
        event.setEventId(1);
        when(eventService.saveEvent(any(Event.class))).thenReturn(event);
        when(fileManager.uploadFile(any(FileDTO.class))).thenReturn(new SavedFileDTO());
        when(jwtService.extractUsername(anyString())).thenReturn("user@domain.com");
        when(userService.findUserByEmail(anyString())).thenReturn(new User());

        mockMvc.perform(post("/events/event")
                        .header("Authorization", "Bearer some-token")
                        .contentType("application/json")
                        .content("{\"event\": {\"id\": 1}, \"fileDTO\": {}}"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.eventId").value(1));
    }

    @Test
    void testEditEvent() throws Exception {
 
        Event event = new Event();
        event.setEventId(1);
        when(eventService.editEvent(eq(1), any(Event.class))).thenReturn(event);


        mockMvc.perform(put("/events/event/1")
                        .contentType("application/json")
                        .content("{\"id\": 1}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.eventId").value(1));
    }

    @Test
    void testDeleteEvent() throws Exception {

        mockMvc.perform(delete("/events/event/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllEventsByUserId() throws Exception {

        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventService.getAllEventsByUserId(1)).thenReturn(events);


        mockMvc.perform(get("/events/user/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    void testGetEventByTitle() throws Exception {

        Event event = new Event();
        event.setTitle("Test Event");
        when(eventService.getEventByTitle("Test Event")).thenReturn(event);


        mockMvc.perform(get("/events/title/Test Event"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Test Event"));
    }

    @Test
    void testGetEventsByEmail() throws Exception {

        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventService.getEventsByEmail("user@domain.com")).thenReturn(events);


        mockMvc.perform(get("/events/email/user@domain.com"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0]").isNotEmpty());
    }
    
}
