package com.partycall.partycallback.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TicketDTO {

    private BigDecimal price;
    private int eventId;
    private String userId;
    
}
