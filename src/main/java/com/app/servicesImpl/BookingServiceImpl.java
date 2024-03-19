package com.app.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookingInDto;
import com.app.dto.BookingOutDto;
import com.app.entities.Booking;
import com.app.entities.BookingStatus;
import com.app.entities.Car;
import com.app.entities.CreditCard;
import com.app.entities.User;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.BookingRepo;
import com.app.repositories.CarRepo;
import com.app.repositories.CreditCardRepo;
import com.app.repositories.UserRepo;
import com.app.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private CreditCardRepo cardRepo;

	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<BookingOutDto> getAllBookings() {

		List<Booking> bookings = this.bookingRepo.findAll();
		List<BookingOutDto> bookingOutDtos = bookings.stream().map(book -> this.modelMapper.map(book,BookingOutDto.class)).collect(Collectors.toList());

		return bookingOutDtos;
	}

	@Override
	public BookingOutDto createBooking(BookingInDto bookingInDto) {

		Booking booking = this.bookingInDtoToBooking(bookingInDto);
		booking.setBookingStatus(BookingStatus.BOOKED);
		Booking savedBooking = this.bookingRepo.save(booking);
		return this.modelMapper.map(savedBooking, BookingOutDto.class);
	}

	@Override
	public BookingOutDto getBookingById(Integer bookingId) {

		Booking booking = this.bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", bookingId));

		return this.modelMapper.map(booking, BookingOutDto.class);
	}
	

	public Booking bookingInDtoToBooking(BookingInDto bookingInDto){
		Booking booking = new Booking();
		
		User curUser = this.userRepo.findById(bookingInDto.getUser_id()).orElseThrow();
		Car curCar = this.carRepo.findById(bookingInDto.getRentedCar_id()).orElseThrow();
		curCar.setBookingStatus(true);
		booking.setStart(bookingInDto.getStart());
		booking.setEnd(bookingInDto.getEnd());
		booking.setPickup(bookingInDto.getPickup());
		booking.setDrop(bookingInDto.getDrop());
		booking.setAmount(bookingInDto.getAmount());
		curUser.addBooking(booking);
		booking.setRentedCar(curCar);
		CreditCard savedCard = this.cardRepo.save(bookingInDto.getCreditCard());
		curUser.setCreditCard(savedCard);
		return booking;
	}
	public BookingInDto bookingToBookingOutDto(Booking booking){
		BookingInDto bookingDto = new BookingInDto();
		bookingDto.setStart(booking.getStart());
		bookingDto.setEnd(booking.getEnd());
		bookingDto.setPickup(booking.getPickup());
		bookingDto.setDrop(booking.getDrop());
		bookingDto.setAmount(booking.getAmount());
		bookingDto.setUser_id(booking.getUser().getId());
		bookingDto.setRentedCar_id(booking.getRentedCar().getId());
		return bookingDto;
	}

	@Override
	public BookingOutDto updateBookingById(BookingInDto bookingInDto, Integer bookingId) {
		Booking b = this.bookingRepo.findById(bookingId).orElseThrow();
		b.setStart(bookingInDto.getStart());
		b.setEnd(bookingInDto.getEnd());
		this.bookingRepo.save(b);
		return this.modelMapper.map(b,BookingOutDto.class);
	}

	@Override
	public BookingOutDto updateStatusById(Integer bookingId, BookingStatus status) {
		Booking b = this.bookingRepo.findById(bookingId).orElseThrow();
		Car c = this.carRepo.findById(b.getRentedCar().getId()).orElseThrow();
		c.setBookingStatus(false);
		this.carRepo.save(c);
		b.setBookingStatus(status);
		this.bookingRepo.save(b);
		return this.modelMapper.map(b, BookingOutDto.class);
	}

	@Override
	public List<BookingOutDto> getAllUserBookings(Integer userId) {
		List<Booking> bookings = this.bookingRepo.findBookingByUserId(userId).orElseThrow();
		List<BookingOutDto> bookingOutDtos = bookings.stream().map(book -> this.modelMapper.map(book,BookingOutDto.class)).collect(Collectors.toList());
		return bookingOutDtos;
	}
}
