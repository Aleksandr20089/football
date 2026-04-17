package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor // ЭТОТ ПАРЕНЬ СОЗДАЕТ КОНСТРУКТОР
public class FootballController {


    private final PlayerService playerService;
    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.savePlayer(player), HttpStatus.CREATED);
    }
}