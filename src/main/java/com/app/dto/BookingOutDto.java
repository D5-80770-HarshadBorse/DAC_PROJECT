package com.app.dto;

import java.time.LocalDate;

import com.app.entities.BookingStatus;
import com.app.entities.Car;
import com.app.entities.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@Setter
@Getter
public class BookingOutDto {
	    private int id;
	    private LocalDate start;
	    private LocalDate end;
	    private String pickup;
	    private String drop;
	    private Double amount;
	    private User user;
	    private Car rentedCar;
	    private BookingStatus bookingStatus;
	}
