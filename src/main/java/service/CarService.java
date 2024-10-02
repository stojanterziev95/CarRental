package service;

import DTOs.AdvancedSearchDTO;
import DTOs.CarAvailabilityDTO;
import models.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Long id, Car car);

    void deleteCar(Long id);

    List<CarAvailabilityDTO> getCarAvailabilityOnDate(LocalDate date);

    List<AdvancedSearchDTO> performAdvancedSearch(String searchTerm);
}
