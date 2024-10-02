package service;

import DTOs.CarAvailability;
import models.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Long id, Car car);

    void deleteCar(Long id);

    List<CarAvailability> getCarAvailability();
}
