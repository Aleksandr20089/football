package com.example.demo.model;

import com.example.demo.model.Club;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Пустой конструктор для Hibernate
@AllArgsConstructor // Конструктор для всех полей (id, name, position, club)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    // Специальный конструктор, чтобы работал твой initDatabase в DemoApplication
    public Player(String name, String position) {
        this.name = name;
        this.position = position;
    }
}