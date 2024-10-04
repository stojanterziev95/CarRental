package com.example.car_rental.models;

import com.example.car_rental.enums.CarStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    @Test
    public void testCarGettersAndSetters() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("Model S");
        car.setBrand("Tesla");
        car.setYear(2020);
        car.setLicensePlate("XYZ-1234");
        car.setRentalPricePerDay(BigDecimal.valueOf(200.00));
        car.setStatus(CarStatus.AVAILABLE);

        assertEquals(1L, car.getId());
        assertEquals("Model S", car.getModel());
        assertEquals("Tesla", car.getBrand());
        assertEquals(2020, car.getYear());
        assertEquals("XYZ-1234", car.getLicensePlate());
        assertEquals(BigDecimal.valueOf(200.00), car.getRentalPricePerDay());
        assertEquals(CarStatus.AVAILABLE, car.getStatus());
    }
}
