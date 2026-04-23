package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ClubRepository clubRepo;
    private final PlayerRepository playerRepo;
    private final CoachRepository coachRepo;

    // КЛУБЫ
    @PostMapping("/clubs")
    public Club addClub(@RequestBody Club club) {
        return clubRepo.save(club);
    }

    @DeleteMapping("/clubs/{id}")
    @Transactional
    public void deleteClub(@PathVariable Long id) {
        Club club = clubRepo.findById(id).orElseThrow(() -> new RuntimeException("Клуб не найден"));

        // РАЗРЫВАЕМ СВЯЗИ, ЧТОБЫ БАЗА ДАЛА УДАЛИТЬ
        if (club.getCoach() != null) {
            club.getCoach().setClub(null);
        }
        club.getPlayers().forEach(p -> p.setClub(null));

        clubRepo.delete(club);
    }

    // ИГРОКИ
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playerRepo.save(player);
    }

    //ТРЕНЕРЫ
    @PostMapping("/coaches")
    public Coach addCoach(@RequestBody Coach coach) {
        return coachRepo.save(coach);
    }
}