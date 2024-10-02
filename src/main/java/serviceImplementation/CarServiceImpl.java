package serviceImplementation;

import DTOs.CarAvailability;
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
    public List<CarAvailability> getCarAvailability() {
        List<Car> cars = carRepository.findAll();
        List<CarAvailability> availabilityList = new ArrayList<>();

        for (Car car : cars) {
            // Logic to determine availability
            // For example, assuming all cars are available from today to next month
            boolean isAvailable = true; // Replace with actual availability logic
            LocalDate availableFrom = LocalDate.now();
            LocalDate availableUntil = availableFrom.plusDays(30);

            availabilityList.add(new CarAvailability(car.getId(), isAvailable, availableFrom, availableUntil));
        }
        return availabilityList;
    }
}
