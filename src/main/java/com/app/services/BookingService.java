package com.app.services;

import java.util.List;

import com.app.dto.BookingInDto;
import com.app.dto.BookingOutDto;
import com.app.entities.Booking;
import com.app.entities.BookingStatus;


public interface BookingService {

	List<BookingOutDto> getAllBookings();
	BookingOutDto createBooking(BookingInDto bookingDto);
	BookingOutDto getBookingById(Integer bookingId);
	BookingOutDto updateBookingById(BookingInDto bookingInDto,Integer bookingId);
	BookingOutDto updateStatusById(Integer bookingId,BookingStatus status);
	List<BookingOutDto> getAllUserBookings(Integer userId);
	
}
