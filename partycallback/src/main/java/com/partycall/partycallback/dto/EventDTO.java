package com.partycall.partycallback.dto;

import com.partycall.partycallback.models.Event;

import lombok.Data;

@Data
public class EventDTO {

    private Event event;

    private FileDTO fileDTO;
    
}