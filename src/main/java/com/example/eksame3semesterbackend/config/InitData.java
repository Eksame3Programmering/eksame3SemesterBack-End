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
        List<Hotel> dummyHotels = generateDummyHotels(50);
    }

    private List<Hotel> generateDummyHotels(int numberOfHotels) {
        List<Hotel> dummyHotels = new ArrayList<>();

        for (int i = 0; i < numberOfHotels; i++) {
            List<Room> rooms = createRooms();
            Hotel hotel = createHotel("HotelName" + i, "HotelStreet" + i, "Copenhagen", "2200", "Denmark","",rooms);
            dummyHotels.add(hotel);
        }

        return dummyHotels;
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();
        int randomNumber = new Random().nextInt(5) + 10; // Generates a random number between 10 and 39
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

    private Hotel createHotel(String name, String street, String city, String zip, String country, String typeAnnotations, List<Room> rooms) {
        LocalDateTime now = LocalDateTime.now();

        // If typeAnnotations is empty, generate random type annotations
        if (typeAnnotations.isEmpty()) {
            typeAnnotations = generateRandomTypeAnnotations();
        }

        Hotel hotel = new Hotel(0L, name, street, city, zip, country, typeAnnotations, new ArrayList<>(), now, now);

        // Set the hotel in each room
        for (Room room : rooms) {
            room.setHotel(hotel);
        }

        // Set the created rooms in the hotel
        hotel.setRooms(rooms);

        // Save the hotel
        return hotelService.createHotel(hotel);
    }

    private Room createRoom(String roomNumber, int numberOfBeds) {
        LocalDateTime now = LocalDateTime.now();

        int pricePerBed = 100; // Set your desired price per bed
        int totalPrice = numberOfBeds * pricePerBed;
        return roomService.createRoom(new Room(0L, roomNumber, numberOfBeds, totalPrice, null, new ArrayList<>(), now, now));
    }

    private String generateRandomTypeAnnotations() {
        List<String> typeAnnotations = new ArrayList<>();
        String[] possibleAnnotations = {"parking for cars", "close to the city"};

        // Ensure at least one of the predefined annotations is included
        int randomIndex = new Random().nextInt(possibleAnnotations.length);
        typeAnnotations.add(possibleAnnotations[randomIndex]);

        // Include additional random annotations if needed
        int numberOfAdditionalAnnotations = new Random().nextInt(2); // Choose a random number of additional annotations (0 to 2)
        for (int i = 0; i < numberOfAdditionalAnnotations; i++) {
            int randomIndexAdditional = new Random().nextInt(possibleAnnotations.length);
            typeAnnotations.add(possibleAnnotations[randomIndexAdditional]);
        }

        // Join the annotations into a single string separated by commas
        return String.join(",", typeAnnotations);
    }
}