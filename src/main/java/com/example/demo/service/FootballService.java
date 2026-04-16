package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.model.Player;
import com.example.demo.repository.ClubRepository;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FootballService {

    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    // Тут создаю клуб
    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    // Тут игроков создаю
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    // Это покупки и трансферы игроков МЕССИИ)
    @Transactional // Если что-то пойдет не так, изменения не запишутся (защита данных)
    public Player transferPlayer(Long playerId, Long newClubId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Игрок с таким ID не найден"));

        Club newClub = clubRepository.findById(newClubId)
                .orElseThrow(() -> new RuntimeException("Клуб с таким ID не найден"));

        player.setClub(newClub);

        return playerRepository.save(player);
    }
}