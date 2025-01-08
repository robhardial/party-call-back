package com.partycall.partycallback.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.partycall.partycallback.models.Venue;

import lombok.Data;

@Data
public class UserTicketsEventsDTO {
    
    private int eventId;

    private String title;

    private Venue venue;

    private BigDecimal price;

    
    private LocalDate startDate;

    
    private LocalTime startTime;

    
    private String imageUrl;

    private UserDTO creator;
}
