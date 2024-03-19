package com.app.services;

import java.util.List;

import com.app.dto.CarInDto;
import com.app.dto.CarOutDto;
public interface CarService {
	List<CarOutDto> getAllCars();
	CarOutDto createCar(CarInDto user);
	CarOutDto getCarById(Integer carId);
	CarOutDto UpdateCarById(Double newRent, Integer carId);
	void deleteCarById(Integer carId);
	String getSlotById(Integer carId);
	List<CarOutDto> createCars(List<CarInDto> carDto);
}
