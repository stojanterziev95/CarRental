package com.example.car_rental.controllers;

import com.example.car_rental.DTOs.AdvancedSearchDTO;
import com.example.car_rental.DTOs.CarAvailabilityDTO;
import com.example.car_rental.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.car_rental.service.CarService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/availability")
    public ResponseEntity<List<CarAvailabilityDTO>> getCarAvailability(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<CarAvailabilityDTO> carAvailabilityList = carService.getCarAvailabilityOnDate(date);
        return ResponseEntity.ok(carAvailabilityList);
    }

    @GetMapping("/advanced")
    public List<AdvancedSearchDTO> advancedSearch(@RequestParam("searchTerm") String searchTerm) {
        return carService.performAdvancedSearch(searchTerm);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<Car>> getCarByPlateId(@PathVariable Integer id) {
//        Car car = carService.getCarByPlateId(id);
//        if (car != null) {
//            return ResponseEntity.ok(new ApiResponse<>(car, "Car retrieved successfully", true));
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ApiResponse<>(null, "Car not found", false));
//        }
//    }

}
