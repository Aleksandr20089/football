package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.model.Player;
import com.example.demo.repository.ClubRepository;
import com.example.demo.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public Player addPlayerToClub(Long clubId, Player player) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Клуб не найден"));

        // Проверка: есть ли в этом клубе игрок с таким номером?
        boolean exists = club.getPlayers().stream()
                .anyMatch(p -> p.getNumber().equals(player.getNumber()));

        if (exists) {
            throw new RuntimeException("В этом клубе уже есть игрок под номером " + player.getNumber());
        }

        player.setClub(club);
        return playerRepository.save(player);
    }

    // Остальной CRUD (findAll, delete, update)
}