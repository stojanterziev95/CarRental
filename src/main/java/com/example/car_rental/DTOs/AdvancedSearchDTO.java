package com.example.car_rental.DTOs;

import com.example.car_rental.models.Car;
import com.example.car_rental.models.Customer;
import com.example.car_rental.models.Reservation;

public class AdvancedSearchDTO {

    private final Car car;
    private final Customer customer;
    private final Reservation reservation;

    public AdvancedSearchDTO(Car car, Customer customer, Reservation reservation) {
        this.car = car;
        this.customer = customer;
        this.reservation = reservation;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Reservation getReservation() {
        return reservation;
    }
}