package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer number;
    private Double price; // Стоимость игрока

    @ManyToOne
    @JoinColumn(name = "club_id") // Если NULL — игрок вне клуба (свободный агент)
    private Club club;
}