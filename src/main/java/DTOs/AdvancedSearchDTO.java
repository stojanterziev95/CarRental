package DTOs;

import models.Car;
import models.Customer;
import models.Reservation;

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