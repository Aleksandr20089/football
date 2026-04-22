package com.example.demo.model;

import com.example.demo.model.Club;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer number; // Номер игрока на футболке

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}