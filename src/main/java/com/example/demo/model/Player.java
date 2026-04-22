package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "Имя игрока не может быть пустым")
    private String name;

    @Min(value = 1, message = "Номер должен быть больше 0")
    @Max(value = 99, message = "Номер не может быть больше 99")
    private Integer number;

    //Позиция на поле
    private String position; // Например: GK, DEF, MID, FWD

    // Ссылка на фото (или аватарку)
    private String photoUrl;

    // Рыночная стоимость игрока
    @PositiveOrZero(message = "Стоимость не может быть отрицательной")
    private Double marketValue;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}