package com.partycall.partycallback.controllers;


import com.partycall.partycallback.dto.TicketDTO;
import com.partycall.partycallback.dto.UserTicketsDTO;
import com.partycall.partycallback.dto.UserTicketsEventsDTO;
import com.partycall.partycallback.services.EventService;
import com.partycall.partycallback.services.JwtService;
import com.partycall.partycallback.services.TicketService;
import com.partycall.partycallback.services.UserService;
import com.partycall.partycallback.models.User;
import com.partycall.partycallback.repositiories.TicketRepository;
import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.models.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private TicketService ticketService;

    private Ticket ticket;

    UserDetails userDetails;

    @MockBean
    EventService eventService;

    @MockBean
    UserService userService;

    @MockBean
    TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket();

        userDetails = new org.springframework.security.core.userdetails.User(
                "user@example.com",
                "password",
                Collections.emptyList()
        );

        // Mock JwtService behavior
        String mockToken = "mock-jwt-token";
        when(jwtService.isValid(eq(mockToken), eq(userDetails))).thenReturn(true);
        when(jwtService.extractUsername(eq(mockToken))).thenReturn("user@example.com");
    }

    @Test
    public void testFindAllTickets() throws Exception {
        List<Ticket> tickets = Arrays.asList(ticket);

        when(ticketService.findAllTickets()).thenReturn(tickets);

        mockMvc.perform(get("/tickets")
                        .header("Authorization", "Bearer mock-jwt-token")) // Pass the mocked token
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    public void testCreateTicket() throws Exception {
        // Create the TicketDTO with test data
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setPrice(new BigDecimal("100.00"));
        ticketDTO.setEventId(1);  
        ticketDTO.setUserId("sampleuser@gmail.com");  

    
        Event mockEvent = new Event();
        mockEvent.setEventId(1);  

        User mockUser = new User();
        mockUser.setUserId(1);  

        when(eventService.getEventById(1)).thenReturn(mockEvent); 
        when(userService.findUserByEmail("sampleuser@gmail.com")).thenReturn(mockUser);  

        // Mock the ticketRepository save method
        Ticket savedTicket = new Ticket();
        savedTicket.setTicketId(1);
        savedTicket.setPrice(new BigDecimal(100.00));
        savedTicket.setEvent(mockEvent);
        savedTicket.setAttendee(mockUser);
        
        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);  // Mock saving the ticket

        mockMvc.perform(post("/tickets/ticket")
                        .header("Authorization", "Bearer mock-jwt-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\": 100.00, \"eventId\": 1, \"userId\": \"sampleuser@gmail.com\"}"))
                        .andExpect(status().isCreated());
    }


    @Test
    public void testGetTicketsByUserEmail() throws Exception {

        UserTicketsDTO userTicketsDTO = new UserTicketsDTO();
        userTicketsDTO.setTicketId(1);
        userTicketsDTO.setPrice(new BigDecimal("100.00"));
        
        UserTicketsEventsDTO eventDTO = new UserTicketsEventsDTO();
        eventDTO.setEventId(1);
        eventDTO.setTitle("Sample Event");
        eventDTO.setStartTime(LocalTime.of(10, 0));  
        eventDTO.setStartDate(LocalDate.of(2025, 1, 10));  
        eventDTO.setPrice(new BigDecimal("50.00"));
        eventDTO.setImageUrl("http://sample.com/image.jpg");

        userTicketsDTO.setEvent(eventDTO);

        List<UserTicketsDTO> ticketsList = Arrays.asList(userTicketsDTO);
        when(ticketService.getTicketsByUserEmail(anyString())).thenReturn(ticketsList);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/tickets/{email}", "user@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))  
                .andExpect(jsonPath("$[0].ticketId").value(1))
                .andExpect(jsonPath("$[0].price").value(100.00))
                .andExpect(jsonPath("$[0].event.eventId").value(1))
                .andExpect(jsonPath("$[0].event.title").value("Sample Event"))
                .andExpect(jsonPath("$[0].event.startTime").value("10:00:00"))
                .andExpect(jsonPath("$[0].event.startDate").value("2025-01-10"))
                .andExpect(jsonPath("$[0].event.price").value(50.00))
                .andExpect(jsonPath("$[0].event.imageUrl").value("http://sample.com/image.jpg"));
    }


}

