package com.example.eksame3semesterbackend.service;

import com.example.eksame3semesterbackend.entity.Hotel;
import com.example.eksame3semesterbackend.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class HotelService {
    @Autowired
    HotelRepository hotelRepository;


    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public  Hotel createHotel(Hotel hotel) {
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        return hotelRepository.findById(id)
                .map(existingHotel -> {
                    existingHotel.setName(updatedHotel.getName());
                    existingHotel.setStreet(updatedHotel.getStreet());
                    existingHotel.setCity(updatedHotel.getCity());
                    existingHotel.setZip(updatedHotel.getZip());
                    existingHotel.setCountry(updatedHotel.getCountry());
                    existingHotel.setUpdated(LocalDateTime.now());
                    return hotelRepository.save(existingHotel);
                })
                .orElse(null);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

}
