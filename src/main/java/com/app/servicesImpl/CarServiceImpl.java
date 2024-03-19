package com.app.servicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CarInDto;
import com.app.dto.CarOutDto;
import com.app.entities.Booking;
import com.app.entities.Car;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.BookingRepo;
import com.app.repositories.CarRepo;
import com.app.services.CarService;
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private BookingRepo bookRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CarOutDto> getAllCars() {
		List<Car> cars = this.carRepo.findAll();
		List<CarOutDto> carDtos = cars.stream().map(car -> this.modelMapper.map(car, CarOutDto.class)).collect(Collectors.toList());
		return carDtos;
	}

	@Override
	public CarOutDto createCar(CarInDto cardto) {
		Car car = this.modelMapper.map(cardto, Car.class);
		Car savedCar = this.carRepo.save(car);
		return this.modelMapper.map(savedCar, CarOutDto.class);
	}

	@Override
	public CarOutDto getCarById(Integer carId) {

		Car car = this.carRepo.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car", " Id ", carId));

		return this.modelMapper.map(car, CarOutDto.class);
	}

	@Override
	public List<CarOutDto> createCars(List<CarInDto> carsDto) {
		List<Car> carList = Arrays.asList(this.modelMapper.map(carsDto, Car[].class));
		return Arrays.asList(this.modelMapper.map(this.carRepo.saveAll(carList), CarOutDto[].class)) ;
	}

	@Override
	public void deleteCarById(Integer carId) {
		this.carRepo.deleteById(carId);
	}

	@Override
	public CarOutDto UpdateCarById(Double newRent, Integer carId) {
		Car car = this.carRepo.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car", " Id ", carId));
		car.setPrice(newRent);
		return this.modelMapper.map(this.carRepo.save(car), CarOutDto.class);
	}

	@Override
	public String getSlotById(Integer carId) {
		Booking b = this.bookRepo.findBookingByCarId(carId).orElseThrow(() -> new ResourceNotFoundException("Car", " Id ", carId));
		return b.getStart()+" -- "+b.getEnd();
	}

}
