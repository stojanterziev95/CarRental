package DTOs;

import java.time.LocalDate;

public class CarAvailability {
    private Long carId;
    private boolean available;
    private LocalDate availableFrom;
    private LocalDate availableUntil;

    // Constructors, getters, and setters
    public CarAvailability(Long carId, boolean available, LocalDate availableFrom, LocalDate availableUntil) {
        this.carId = carId;
        this.available = available;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(LocalDate availableUntil) {
        this.availableUntil = availableUntil;
    }
}
