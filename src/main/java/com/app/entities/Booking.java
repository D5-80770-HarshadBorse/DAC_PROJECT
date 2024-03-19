package com.app.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Bookings")

public class Booking extends BaseEntity {

	        @DateTimeFormat(pattern = "yyyy-MM-dd")
	        @Column(name = "start_date", nullable = false)
	        private LocalDate start;
	        
	        @DateTimeFormat(pattern = "yyyy-MM-dd")
	        @Column(name = "end_date", nullable = false)
	        private LocalDate end;
	        
	        @Column(name = "pickup_location", nullable = false)
	        private String pickup;

	        @Column(name = "drop_location", nullable = false)
	        private String drop;
	        
	        @Column(name = "total_amount", nullable = false)
	        private Double amount;
	        
	        @ManyToOne
	        @JoinColumn(name ="user_id", nullable = false)
	        private User user;
	        
	        @OneToOne
	        @JoinColumn(name ="car_id", nullable = false)
	        private Car rentedCar;
	        
	        @Column(length = 32)
	        @Enumerated(value = EnumType.STRING)
	        private BookingStatus bookingStatus;
}

