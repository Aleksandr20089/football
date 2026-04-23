package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FootballService {
    private final ClubRepository clubRepo;
    private final PlayerRepository playerRepo;
    private final CoachRepository coachRepo;

    // 1. Покупка игрока (Переход из вне клуба -> в клуб)
    @Transactional
    public void buyPlayer(Long clubId, Long playerId) {
        Club club = clubRepo.findById(clubId).orElseThrow(() -> new RuntimeException("Клуб не найден"));
        Player player = playerRepo.findById(playerId).orElseThrow(() -> new RuntimeException("Игрок не найден"));

        if (player.getClub() != null) throw new RuntimeException("Игрок уже занят другим клубом");
        if (club.getBalance() < player.getPrice()) throw new RuntimeException("Недостаточно средств на балансе клуба");

        club.setBalance(club.getBalance() - player.getPrice());
        player.setClub(club);

        playerRepo.save(player);
        clubRepo.save(club);
    }

    // 2. Продажа игрока (Переход из клуба -> вне клуба)
    @Transactional
    public void sellPlayer(Long clubId, Long playerId) {
        Club club = clubRepo.findById(clubId).orElseThrow(() -> new RuntimeException("Клуб не найден"));
        Player player = playerRepo.findById(playerId).orElseThrow(() -> new RuntimeException("Игрок не найден"));

        if (!club.equals(player.getClub())) throw new RuntimeException("Этот игрок не принадлежит данному клубу");

        club.setBalance(club.getBalance() + player.getPrice());
        player.setClub(null); // Теперь он свободный агент

        playerRepo.save(player);
        clubRepo.save(club);
    }

    // 3. Найм тренера
    @Transactional
    public void hireCoach(Long clubId, Long coachId) {
        Club club = clubRepo.findById(clubId).orElseThrow();
        Coach coach = coachRepo.findById(coachId).orElseThrow();

        if (club.getBalance() < coach.getPrice()) throw new RuntimeException("Нет денег на тренера");

        club.setBalance(club.getBalance() - coach.getPrice());
        club.setCoach(coach);
        clubRepo.save(club);
    }
}