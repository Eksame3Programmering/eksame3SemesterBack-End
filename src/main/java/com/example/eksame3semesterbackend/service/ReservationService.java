package com.example.eksame3semesterbackend.service;

import com.example.eksame3semesterbackend.entity.Reservation;
import com.example.eksame3semesterbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setCreated(LocalDateTime.now());
        reservation.setUpdated(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .map(existingReservation -> {
                    existingReservation.setRoom(updatedReservation.getRoom());
                    existingReservation.setGuest(updatedReservation.getGuest());
                    existingReservation.setDays(updatedReservation.getDays());
                    existingReservation.setUpdated(LocalDateTime.now());
                    return reservationRepository.save(existingReservation);
                })
                .orElse(null);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
