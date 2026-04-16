package com.example.demo.repository;

import com.example.demo.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    // Здесь уже "встроены" методы: save(), findAll(), findById(), delete()
}