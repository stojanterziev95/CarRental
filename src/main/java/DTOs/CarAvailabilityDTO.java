package DTOs;

public class CarAvailabilityDTO {
    private final String plateId;
    private final boolean available;
    private final String reservationStatus;

    public CarAvailabilityDTO(String plateId, boolean available) {
        // Validate plateId is not null or empty
        if (plateId == null || plateId.isEmpty()) {
            throw new IllegalArgumentException("Plate ID cannot be null or empty");
        }

        this.plateId = plateId;
        this.available = available;
        this.reservationStatus = available ? "Available" : "Unavailable";
    }

    // Getter for plateId
    public String getPlateId() {
        return plateId;
    }

    // Getter for availability status
    public boolean isAvailable() {
        return available;
    }

    // Getter for reservation status
    public String getReservationStatus() {
        return reservationStatus;
    }

    @Override
    public String toString() {
        return "CarAvailabilityDTO{" +
                "plateId='" + plateId + '\'' +
                ", available=" + available +
                ", reservationStatus='" + reservationStatus + '\'' +
                '}';
    }
}
