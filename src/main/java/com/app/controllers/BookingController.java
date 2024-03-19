package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingInDto;
import com.app.dto.BookingOutDto;
import com.app.entities.Booking;
import com.app.entities.BookingStatus;
import com.app.services.BookingService;


@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
     private BookingService bookingsService;
	@PostMapping("/create")
	public ResponseEntity<BookingOutDto> createBooking(@RequestBody BookingInDto bookingDto) {
		System.out.println(bookingDto);
		BookingOutDto createBooking = this.bookingsService.createBooking(bookingDto);
		return new ResponseEntity<>(createBooking, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{bookingId}")
	public ResponseEntity<BookingOutDto> updateBooking(@RequestBody BookingInDto bookingInDto,@PathVariable Integer bookingId)
	{
		return new ResponseEntity<BookingOutDto>(this.bookingsService.updateBookingById(bookingInDto, bookingId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{bookingId}/{status}")
	public ResponseEntity<BookingOutDto> updateStatus(@PathVariable Integer bookingId,@PathVariable BookingStatus status)
	{
		return new ResponseEntity<BookingOutDto>(this.bookingsService.updateStatusById(bookingId, status),HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BookingOutDto>> getAllBookings() {
		return ResponseEntity.ok(this.bookingsService.getAllBookings());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<List<BookingOutDto>> getAllUserBookings(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.bookingsService.getAllUserBookings(userId));
	}

	
	@GetMapping("/get/{bookingId}")
	public ResponseEntity<BookingOutDto> getSingleBooking(@PathVariable Integer bookingId) {
		return ResponseEntity.ok(this.bookingsService.getBookingById(bookingId));
	}
	

}
