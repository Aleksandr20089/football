package com.example.demo.model;

import com.example.demo.model.Club;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor  // Обязательно! Нужен для Hibernate, чтобы создавать пустые объекты
@AllArgsConstructor // Обязательно! Позволяет создавать объекты через new Player(...)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}