package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("/club/{clubId}")
    public ResponseEntity<Player> addPlayer(@PathVariable Long clubId, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.addPlayerToClub(clubId, player));
    }
    // Добавь сюда остальные GET/DELETE методы
}