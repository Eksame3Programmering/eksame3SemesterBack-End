package com.example.eksame3semesterbackend.repository;

import com.example.eksame3semesterbackend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h, COUNT(r) FROM Hotel h LEFT JOIN h.rooms r GROUP BY h")
    List<Object[]> findHotelsWithRoomCount();
}
