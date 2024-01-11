package com.example.eksame3semesterbackend.config;

import com.example.eksame3semesterbackend.entity.Hotel;
import com.example.eksame3semesterbackend.entity.Room;
import com.example.eksame3semesterbackend.service.HotelService;
import com.example.eksame3semesterbackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Override
    public void run(String... args) throws Exception {

    //List<Hotel> dummyHotels = generateDummyHotels(50);

    }

    private List<Hotel> generateDummyHotels(int numberOfHotels) {
        List<Hotel> dummyHotels = new ArrayList<>();

        for (int i = 0; i < numberOfHotels; i++) {
            List<Room> rooms = createRooms();
            Hotel hotel = createHotel("HotelName" + i, "HotelStreet" + i, "Copenhagen", "2200", "Denmark", rooms);
            dummyHotels.add(hotel);
        }

        return dummyHotels;
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();
        int randomNumber = new Random().nextInt(30) + 10; // Generates a random number between 10 and 39
        for (int i = 0; i < randomNumber; i++) {
            Room room = createRoom("RoomNumber" + i, getRandomNumberOfBeds());
            rooms.add(room);
        }
        return rooms;
    }
    private int getRandomNumberOfBeds() {
        // Generate a random number between 2 and 4
        return ThreadLocalRandom.current().nextInt(2, 5);
    }

    private Hotel createHotel(String name, String street, String city, String zip, String country, List<Room> rooms) {
        LocalDateTime now = LocalDateTime.now();
        Hotel hotel = new Hotel(0L, name, street, city, zip, country, new ArrayList<>(), now, now);

        // Set the hotel in each room
        for (Room room : rooms) {
            room.setHotel(hotel);
        }

        // Set the created rooms in the hotel
        hotel.setRooms(rooms);

        // Save the hotel

        return hotelService.createHotel(hotel);

        //if you dont want to save
       //return hotel;
    }

    private Room createRoom(String roomNumber, int numberOfBeds) {
        LocalDateTime now = LocalDateTime.now();
        return roomService.createRoom(new Room(0L, roomNumber, numberOfBeds, null, new ArrayList<>(), now, now));
    }
}