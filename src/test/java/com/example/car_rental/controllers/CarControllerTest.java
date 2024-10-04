package com.example.car_rental.controllers;



import com.example.car_rental.DTOs.AdvancedSearchDTO;
import com.example.car_rental.DTOs.CarAvailabilityDTO;
import com.example.car_rental.models.Car;
import com.example.car_rental.models.CarStatus;
import com.example.car_rental.models.Category;
import com.example.car_rental.models.Reservation;
import com.example.car_rental.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        Category category = new Category(); // Assuming you have a Category model with proper fields
        category.setId(1L);

        car1 = new Car(1L, "Model S", "Tesla", 2022, "TES123", BigDecimal.valueOf(200), CarStatus.AVAILABLE, category, Collections.emptyList());
        car2 = new Car(2L, "Model 3", "Tesla", 2021, "TES456", BigDecimal.valueOf(150), CarStatus.AVAILABLE, category, Collections.emptyList());
    }

    @Test
    void testGetAllCars() throws Exception {
        List<Car> cars = Arrays.asList(car1, car2);
        when(carService.getAllCars()).thenReturn(cars);

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(car1.getId()))
                .andExpect(jsonPath("$[1].id").value(car2.getId()));
    }

    @Test
    void testGetCarById() throws Exception {
        when(carService.getCarById(1L)).thenReturn(car1);

        mockMvc.perform(get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(car1.getId()))
                .andExpect(jsonPath("$.model").value(car1.getModel()));
    }

    @Test
    void testCreateCar() throws Exception {
        when(carService.createCar(any(Car.class))).thenReturn(car1);

        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\": \"Model S\", \"brand\": \"Tesla\", \"year\": 2022, \"licensePlate\": \"TES123\", \"rentalPricePerDay\": 200.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value(car1.getModel()))
                .andExpect(jsonPath("$.brand").value(car1.getBrand()));
    }

    @Test
    void testUpdateCar() throws Exception {
        when(carService.updateCar(eq(1L), any(Car.class))).thenReturn(car1);

        mockMvc.perform(put("/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\": \"Model S\", \"brand\": \"Tesla\", \"year\": 2022, \"licensePlate\": \"TES123\", \"rentalPricePerDay\": 200.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value(car1.getModel()))
                .andExpect(jsonPath("$.brand").value(car1.getBrand()));
    }

    @Test
    void testDeleteCar() throws Exception {
        doNothing().when(carService).deleteCar(1L);

        mockMvc.perform(delete("/cars/1"))
                .andExpect(status().isNoContent());

        verify(carService, times(1)).deleteCar(1L);
    }

    @Test
    void testGetCarAvailability() throws Exception {
        List<CarAvailabilityDTO> availabilityList = Arrays.asList(
                new CarAvailabilityDTO("m-1233-ak", true),
                new CarAvailabilityDTO("b-2345-bo", false)
        );

        when(carService.getCarAvailabilityOnDate(any(LocalDate.class))).thenReturn(availabilityList);

        mockMvc.perform(get("/cars/availability")
                        .param("date", "2023-12-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].car.model").value(car1.getModel()))
                .andExpect(jsonPath("$[1].available").value(false));
    }

    @Test
    void testAdvancedSearch() throws Exception {
        AdvancedSearchDTO advancedSearchResult = new AdvancedSearchDTO(car1, null, null);
        List<AdvancedSearchDTO> searchResults = Arrays.asList(advancedSearchResult);

        when(carService.performAdvancedSearch("Tesla")).thenReturn(searchResults);

        mockMvc.perform(get("/cars/advanced")
                        .param("searchTerm", "Tesla"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].car.model").value(car1.getModel()));
    }
}
