package com.example.demo.model;

import com.example.demo.model.Club;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer experienceYears;

    @OneToOne(mappedBy = "coach")
    @JsonIgnore // Чтобы не было бесконечной рекурсии в JSON
    private Club club;
}