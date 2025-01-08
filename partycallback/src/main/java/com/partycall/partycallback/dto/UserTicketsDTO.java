package com.partycall.partycallback.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UserTicketsDTO {

    private int ticketId;
    private BigDecimal price;
    private UserTicketsEventsDTO event;
    
}
