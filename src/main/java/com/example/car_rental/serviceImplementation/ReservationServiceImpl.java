package com.example.car_rental.serviceImplementation;

import com.example.car_rental.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car_rental.repository.ReservationRepository;
import com.example.car_rental.service.ReservationService;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        existingReservation.setStartDate(reservation.getStartDate());
        existingReservation.setEndDate(reservation.getEndDate());
        existingReservation.setTotalPrice(reservation.getTotalPrice());
        existingReservation.setStatus(reservation.getStatus());
        existingReservation.setCar(reservation.getCar());
        existingReservation.setCustomer(reservation.getCustomer());
        return reservationRepository.save(existingReservation);
    }
    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
