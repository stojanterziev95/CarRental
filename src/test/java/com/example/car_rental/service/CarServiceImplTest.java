package com.example.car_rental.service;

import com.example.car_rental.models.Car;
import com.example.car_rental.repository.CarRepository;
import com.example.car_rental.serviceImplementation.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        car = new Car();
        car.setId(1L);
        car.setModel("Model S");
    }

    @Test
    public void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(car));
        assertEquals(1, carService.getAllCars().size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testGetCarById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        assertEquals(car, carService.getCarById(1L));
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCar() {
        when(carRepository.save(car)).thenReturn(car);
        assertEquals(car, carService.createCar(car));
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testUpdateCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        Car updatedCar = new Car();
        updatedCar.setModel("Model 3");
        carService.updateCar(1L, updatedCar);

        assertEquals("Model 3", car.getModel());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testDeleteCar() {
        carService.deleteCar(1L);
        verify(carRepository, times(1)).deleteById(1L);
    }
}
