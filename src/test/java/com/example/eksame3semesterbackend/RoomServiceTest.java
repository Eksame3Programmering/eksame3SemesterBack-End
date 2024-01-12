package com.example.eksame3semesterbackend;

import com.example.eksame3semesterbackend.entity.Room;
import com.example.eksame3semesterbackend.repository.RoomRepository;
import com.example.eksame3semesterbackend.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest

public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void getAllRooms() {
        // Arrange
        Long hotelId = 1L;
        when(roomRepository.findAllRoomsInHotel(hotelId)).thenReturn(Arrays.asList(
                new Room(1L, "101", 2, 100, null, null, LocalDateTime.now(), LocalDateTime.now()),
                new Room(2L, "202", 1, 150, null, null, LocalDateTime.now(), LocalDateTime.now())
        ));

        // Act
        List<Room> result = roomService.findAllRoomsInHotel(hotelId);

        // Assert
        assertEquals(2, result.size());
    }

    private void assertEquals(String i, String size) {

    }

    @Test
    void getRoomById() {
        // Arrange
        Long id = 1L;
        when(roomRepository.findById(id)).thenReturn(Optional.of(new Room(id, "101", 2, 100, null, null, LocalDateTime.now(), LocalDateTime.now())));

        // Act
        Optional<Room> result = roomService.getRoomById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(Math.toIntExact(id), Math.toIntExact(result.get().getId()));
    }




    private void assertEquals(int intExact, int intExact1) {
    }

    @Test
    void updateRoom() {
        // Arrange
        Long id = 1L;
        Room existingRoom = new Room(id, "101", 2, 100, null, null, LocalDateTime.now(), LocalDateTime.now());
        Room updatedRoom = new Room(id, "101_updated", 3, 150, null, null, LocalDateTime.now(), LocalDateTime.now());

        when(roomRepository.findById(id)).thenReturn(Optional.of(existingRoom));

        // Act
        Room result = roomService.updateRoom(id, updatedRoom);

        // Assert
        assertEquals(updatedRoom.getRoomNumber(), result.getRoomNumber());
        assertEquals(updatedRoom.getNumberOfBeds(), result.getNumberOfBeds());
        // Add more assertions based on your requirements
    }

    @Test
    void deleteRoom() {
        // Arrange
        Long id = 1L;

        // Act
        roomService.deleteRoom(id);

        // Assert
        verify(roomRepository, times(1)).deleteById(id);
    }

}
