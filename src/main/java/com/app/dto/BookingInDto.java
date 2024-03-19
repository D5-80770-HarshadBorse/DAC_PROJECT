package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Car;
import com.app.entities.CreditCard;
import com.app.entities.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@Setter
@Getter
public class BookingInDto {
    private LocalDate start;
    private LocalDate end;
    private String pickup;
    private String drop;
    private Double amount;
    private int user_id;
    private int rentedCar_id;
    private CreditCard creditCard;
}
