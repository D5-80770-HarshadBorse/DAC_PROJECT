package com.app.dto;

import com.app.entities.Booking;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarInDto {
    private String brand;
    private Integer rating;
    private String carName;
    private String imgUrl;
    private String model;
    private Double price;
    private String speed;
    private String gps;
    private String seatType;
    private String automatic;
    private String description;
}
