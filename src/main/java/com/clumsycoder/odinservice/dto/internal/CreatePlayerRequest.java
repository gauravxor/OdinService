package com.clumsycoder.odinservice.dto.internal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePlayerRequest {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}