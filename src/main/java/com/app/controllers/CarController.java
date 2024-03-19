package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CarInDto;
import com.app.dto.CarOutDto;
import com.app.dto.UserInDto;
import com.app.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping("/add")
	public ResponseEntity<CarOutDto> addCar(@RequestBody CarInDto carDto) {
		CarOutDto createCarDto = this.carService.createCar(carDto);
		return new ResponseEntity<>(createCarDto, HttpStatus.CREATED);
	}
	@PostMapping("/addAll")
	public ResponseEntity<CarOutDto> addCars(@RequestBody List<CarInDto> carDto) {
		List<CarOutDto> createCarsDto = this.carService.createCars(carDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{carId}/{newRent}")
	public ResponseEntity<CarOutDto> UpdateCars(@PathVariable Integer carId,@PathVariable Double newRent) {
		this.carService.UpdateCarById(newRent, carId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<CarOutDto> DeletetCars(@PathVariable Integer carId) {
		this.carService.deleteCarById(carId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CarOutDto>> getAllCars() {
		return ResponseEntity.ok(this.carService.getAllCars());
	}

	@GetMapping("/get/{carId}")
	public ResponseEntity<CarOutDto> getSingleCar(@PathVariable Integer carId) {
		return ResponseEntity.ok(this.carService.getCarById(carId));
	}
	@GetMapping("/slot/{carId}")
	public ResponseEntity<String> bookingSlot(@PathVariable Integer carId) {
		return ResponseEntity.ok(this.carService.getSlotById(carId));
	}
}
