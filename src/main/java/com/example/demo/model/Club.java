package com.example.demo.model;

import com.example.demo.model.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Клуб с таким названием должен быть один
    private String name;

    private String city;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // Инициализируем сразу, чтобы не было ошибок при добавлении
    private List<Player> players = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;
}