package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false)
    private Double balance = 0.0;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Coach coach;

    // Важно: orphanRemoval = true вычистит игроков из базы при удалении клуба
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public Double getBalance() {
        return balance == null ? 0.0 : balance;
    }
}