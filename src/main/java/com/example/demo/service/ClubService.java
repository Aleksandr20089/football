package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}
