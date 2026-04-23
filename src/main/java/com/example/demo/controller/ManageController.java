package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ClubRepository clubRepo;
    private final PlayerRepository playerRepo;
    private final CoachRepository coachRepo;

    @PostMapping("/buy-player")
    @Transactional
    public ResponseEntity<?> buyPlayer(@RequestParam Long clubId, @RequestParam Long playerId) {
        Club club = clubRepo.findById(clubId).orElseThrow();
        Player player = playerRepo.findById(playerId).orElseThrow();

        if (club.getBalance() < player.getPrice()) {
            return ResponseEntity.badRequest().body("Недостаточно денег на балансе клуба!");
        }

        club.setBalance(club.getBalance() - player.getPrice());
        player.setClub(club);

        playerRepo.save(player);
        clubRepo.save(club);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hire-coach")
    @Transactional
    public ResponseEntity<?> hireCoach(@RequestParam Long clubId, @RequestParam Long coachId) {
        Club club = clubRepo.findById(clubId).orElseThrow();
        Coach coach = coachRepo.findById(coachId).orElseThrow();

        if (club.getBalance() < coach.getPrice()) {
            return ResponseEntity.badRequest().body("Нет денег на контракт тренера!");
        }

        // Если у клуба уже был тренер, отвязываем его
        if (club.getCoach() != null) {
            club.getCoach().setClub(null);
        }

        club.setBalance(club.getBalance() - coach.getPrice());
        coach.setClub(club);
        club.setCoach(coach);

        coachRepo.save(coach);
        clubRepo.save(club);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sell-player")
    @Transactional
    public ResponseEntity<?> sellPlayer(@RequestParam Long clubId, @RequestParam Long playerId) {
        Club club = clubRepo.findById(clubId).orElseThrow();
        Player player = playerRepo.findById(playerId).orElseThrow();

        club.setBalance(club.getBalance() + (player.getPrice() * 0.8));
        player.setClub(null);

        playerRepo.save(player);
        clubRepo.save(club);
        return ResponseEntity.ok().build();
    }
}