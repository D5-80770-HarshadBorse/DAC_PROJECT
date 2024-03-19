package com.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
	@Query( 
	        nativeQuery = true, 
	        value 
	        = "SELECT * FROM bookings b where b.car_id=:carId") 
	       Optional<Booking> findBookingByCarId(@Param("carId") int carId);
	@Query( 
	        nativeQuery = true, 
	        value 
	        = "SELECT * FROM bookings b where b.user_id=:userId") 
	       Optional<List<Booking>> findBookingByUserId(@Param("userId") int userId);
}
