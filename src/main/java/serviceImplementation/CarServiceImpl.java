package serviceImplementation;

import DTOs.AdvancedSearchDTO;
import DTOs.CarAvailabilityDTO;
import models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CarRepository;
import service.CarService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        existingCar.setModel(car.getModel());
        existingCar.setBrand(car.getBrand());
        existingCar.setYear(car.getYear());
        existingCar.setLicensePlate(car.getLicensePlate());
        existingCar.setRentalPricePerDay(car.getRentalPricePerDay());
        existingCar.setStatus(car.getStatus());
        existingCar.setCategory(car.getCategory());
        return carRepository.save(existingCar);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarAvailabilityDTO> getCarAvailabilityOnDate(LocalDate date) {
        return carRepository.findCarAvailabilityByDate(date);
    }

    @Override
    public List<AdvancedSearchDTO> performAdvancedSearch(String searchTerm) {
        return carRepository.advancedSearch(searchTerm);
    }
}
