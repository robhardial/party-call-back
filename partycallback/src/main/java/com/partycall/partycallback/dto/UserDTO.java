package com.partycall.partycallback.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;


    public UserDTO(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
}
