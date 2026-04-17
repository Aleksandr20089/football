package com.example.demo.model;

import com.example.demo.model.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // OneToMany: Один клуб — список игроков
    // mappedBy = "club": говорит, что главная связь описана в поле 'club' класса Player
    // cascade = CascadeType.ALL: удалил клуб — удалились и игроки
    // orphanRemoval = true: если удалить игрока из списка, он удалится и из БД
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Player> players;
}