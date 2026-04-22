package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.repository.ClubRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    // Тут находим все клубы  все клубы
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    // Найти по ID
    public Club findById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клуб не найден с id: " + id));
    }

    // Создание клуба
    @Transactional
    public Club save(Club club) {
        if (clubRepository.existsByName(club.getName())) {
            throw new RuntimeException("Клуб с названием '" + club.getName() + "' уже существует!");
        }
        return clubRepository.save(club);
    }

    // Обновление названия, тренера и владельца
    @Transactional
    public Club update(Long id, Club details) {
        Club club = findById(id);

        // 1. Меняем название (если оно пришло в запросе)
        if (details.getName() != null) {
            club.setName(details.getName());
        }

        // 2. Назначаем тренера
        if (details.getCoach() != null) {
            club.setCoach(details.getCoach());
        }

        // 3. Назначаем владельца
        if (details.getOwner() != null) {
            club.setOwner(details.getOwner());
        }

        return clubRepository.save(club);
    }

    //
    @Transactional
    public void delete(Long id) {
        if (!clubRepository.existsById(id)) {
            throw new RuntimeException("Нельзя удалить: клуб не найден");
        }
        clubRepository.deleteById(id);
    }
}