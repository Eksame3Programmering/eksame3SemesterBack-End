package com.example.eksame3semesterbackend.repository;

import com.example.eksame3semesterbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
