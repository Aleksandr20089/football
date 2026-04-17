package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok сам создаст конструктор для инъекции
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }
}