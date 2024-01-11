package com.example.eksame3semesterbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class HotelDTO {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
    private int rooms;
    private LocalDateTime created;
    private LocalDateTime updated;
}
