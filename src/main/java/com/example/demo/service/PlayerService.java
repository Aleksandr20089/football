package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.model.Player;
import com.example.demo.repository.ClubRepository;
import com.example.demo.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    // Получить вообще всех игроков
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    //Найти одного по ID
    public Player findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Игрок не найден с id: " + id));
    }

    //Простое добавление в клуб
    @Transactional
    public Player addPlayerToClub(Long clubId, Player player) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Клуб не найден"));

        validatePlayerNumber(club, player.getNumber());

        player.setClub(club);
        return playerRepository.save(player);
    }

    // UPDATE - Метод "Трансфер" (Покупка игрока с проверкой бюджета)
    @Transactional
    public Player buyPlayer(Long clubId, Player player, Double price) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Клуб не найден"));

        // Проверяем бюджет владельца, если он есть
        if (club.getOwner() != null) {
            if (club.getOwner().getBudget() < price) {
                throw new RuntimeException("У владельца " + club.getOwner().getName() + " не хватает денег! Нужно: " + price);
            }
            // Списываем деньги у владельца
            club.getOwner().setBudget(club.getOwner().getBudget() - price);
        }

        validatePlayerNumber(club, player.getNumber());

        player.setClub(club);
        return playerRepository.save(player);
    }

    //  Просто обновить данные (имя, номер)
    @Transactional
    public Player update(Long id, Player details) {
        Player player = findById(id);
        player.setName(details.getName());
        player.setNumber(details.getNumber());
        return playerRepository.save(player);
    }

    // Удалить игрока
    @Transactional
    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Нельзя удалить: игрок не найден");
        }
        playerRepository.deleteById(id);
    }

    // Вспомогательный метод для проверки номера (чтобы не дублировать код)
    private void validatePlayerNumber(Club club, Integer number) {
        boolean exists = club.getPlayers().stream()
                .anyMatch(p -> p.getNumber().equals(number));
        if (exists) {
            throw new RuntimeException("В клубе '" + club.getName() + "' уже занят номер " + number);
        }
    }
}