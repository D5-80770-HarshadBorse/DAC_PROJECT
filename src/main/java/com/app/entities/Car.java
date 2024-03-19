package com.app.entities;



import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@Table(name = "car")
public class Car extends BaseEntity {

        @Column
        private String brand;

        @Column
        private Integer rating;

        @Column
        private String carName;

        @Column
        private String imgUrl;

        @Column(length = 250)
        private String model;
        
        @Column
        private Double price;
        
        @Column
        private String speed;
        
        @Column
        private String gps;
        
        @Column
        private String seatType;
        
        @Column
        private String automatic;
        
        @Column(length = 250)
        private String description;
        
        @Column(name="booking_status")
        @Value(value = "false")
        private boolean bookingStatus;

        
}
