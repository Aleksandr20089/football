package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    // 1. Получить вообще всех игроков
    @GetMapping
    public ResponseEntity<List<Player>> getAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    // 2. Получить одного игрока по ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    // 3. Просто добавить игрока в клуб
    @PostMapping("/club/{clubId}")
    public ResponseEntity<Player> addPlayer(@PathVariable Long clubId, @Valid @RequestBody Player player) {
        return ResponseEntity.ok(playerService.addPlayerToClub(clubId, player));
    }

    // 4. КУПИТЬ игрока (для фроната с ценой и бюджетом)
    @PostMapping("/buy/{clubId}")
    public ResponseEntity<Player> buyPlayer(
            @PathVariable Long clubId,
            @Valid @RequestBody Player player,
            @RequestParam Double price) {
        return ResponseEntity.ok(playerService.buyPlayer(clubId, player, price));
    }

    // 5. Обновить данные игрока
    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @Valid @RequestBody Player player) {
        return ResponseEntity.ok(playerService.update(id, player));
    }

    // 6. Удалить игрока (Расторгнуть контракт)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}