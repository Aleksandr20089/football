package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.repository.ClubRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public List<Club> findAll() { return clubRepository.findAll(); }

    public Club findById(Long id) {
        return clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Клуб не найден"));
    }

    @Transactional
    public Club save(Club club) {
        // Проверка на уникальность названия клуба
        if (clubRepository.existsByName(club.getName())) {
            throw new RuntimeException("Клуб с таким названием уже есть!");
        }
        return clubRepository.save(club);
    }

    @Transactional
    public void delete(Long id) { clubRepository.deleteById(id); }
}