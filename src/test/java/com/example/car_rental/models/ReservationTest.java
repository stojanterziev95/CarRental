package com.example.car_rental.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {

    @Test
    public void testReservationGettersAndSetters() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStartDate(LocalDateTime.now());
        reservation.setEndDate(LocalDateTime.now().plusDays(2));
        reservation.setTotalPrice(BigDecimal.valueOf(400.00));
        reservation.setStatus(ReservationStatus.CONFIRMED);

        assertEquals(1L, reservation.getId());
        assertEquals(reservation.getStartDate(), reservation.getStartDate());
        assertEquals(reservation.getEndDate(), reservation.getEndDate());
        assertEquals(BigDecimal.valueOf(400.00), reservation.getTotalPrice());
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
    }
}