package com.example.demo.controller;

import com.example.demo.repository.ClubRepository;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ClubRepository clubRepo;
    private final PlayerRepository playerRepo;
    private final CoachRepository coachRepo;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("clubs", clubRepo.findAll());

        model.addAttribute("freePlayers", playerRepo.findAll().stream()
                .filter(p -> p.getClub() == null)
                .toList());

        model.addAttribute("freeCoaches", coachRepo.findAll().stream()
                .filter(c -> c.getClub() == null)
                .toList());

        return "index";
    }
}