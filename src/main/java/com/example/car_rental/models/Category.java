package com.example.car_rental.models;

import com.example.car_rental.enums.CarCategory;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Category name cannot be null")
    @Enumerated(EnumType.STRING)
    private CarCategory categoryName;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Car> cars;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CarCategory categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

