package com.example.car_rental.models;

import com.example.car_rental.enums.CarStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Model cannot be empty")
    @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
    private String model;

    @NotEmpty(message = "Brand cannot be empty")
    @Size(min = 1, max = 50, message = "Brand must be between 1 and 50 characters")
    private String brand;

    @NotNull(message = "Year cannot be null")
    @DecimalMin(value = "1886", message = "Year must be greater than or equal to 1886") // First car was invented in 1886
    private int year;

    @NotEmpty(message = "License plate cannot be empty")
    @Size(min = 1, max = 15, message = "License plate must be between 1 and 15 characters")
    private String licensePlate;

    @NotNull(message = "Rental price per day cannot be null")
    @DecimalMin(value = "0.0", message = "Rental price per day must be greater than or equal to 0")
    private BigDecimal rentalPricePerDay;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private CarStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category cannot be null")
    private Category category;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;

    // Default constructor
    public Car() {
    }

    // Parameterized constructor
    public Car(Long id, String model, String brand, int year, String licensePlate, BigDecimal rentalPricePerDay, CarStatus status, Category category, List<Reservation> reservations) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.status = status;
        this.category = category;
        this.reservations = reservations;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
