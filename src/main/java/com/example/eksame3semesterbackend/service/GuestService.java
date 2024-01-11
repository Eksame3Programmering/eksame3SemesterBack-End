package com.example.eksame3semesterbackend.service;

import com.example.eksame3semesterbackend.entity.Guest;
import com.example.eksame3semesterbackend.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    public Guest createGuest(Guest guest) {
        guest.setCreated(LocalDateTime.now());
        guest.setUpdated(LocalDateTime.now());
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Long id, Guest updatedGuest) {
        return guestRepository.findById(id)
                .map(existingGuest -> {
                    existingGuest.setUsername(updatedGuest.getUsername());
                    existingGuest.setFirstName(updatedGuest.getFirstName());
                    existingGuest.setLastName(updatedGuest.getLastName());
                    existingGuest.setEmail(updatedGuest.getEmail());
                    existingGuest.setPhoneNumber(updatedGuest.getPhoneNumber());
                    existingGuest.setUpdated(LocalDateTime.now());
                    return guestRepository.save(existingGuest);
                })
                .orElse(null);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

}
