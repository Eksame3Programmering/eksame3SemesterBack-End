package com.example.eksame3semesterbackend.service;

import com.example.eksame3semesterbackend.entity.Room;
import com.example.eksame3semesterbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        room.setCreated(LocalDateTime.now());
        room.setUpdated(LocalDateTime.now());
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        return roomRepository.findById(id)
                .map(existingRoom -> {
                    existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
                    existingRoom.setNumberOfBeds(updatedRoom.getNumberOfBeds());
                    existingRoom.setHotel(updatedRoom.getHotel());
                    existingRoom.setReservations(updatedRoom.getReservations());
                    existingRoom.setUpdated(LocalDateTime.now());
                    return roomRepository.save(existingRoom);
                })
                .orElse(null);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
