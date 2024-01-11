package com.example.eksame3semesterbackend.repository;

import com.example.eksame3semesterbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoomRepository extends JpaRepository<Room, Long> {
}
