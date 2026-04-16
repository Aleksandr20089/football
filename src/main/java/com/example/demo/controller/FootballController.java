package com.example.demo.controller;

import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FootballController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "index"; // Это имя файла index.html в templates
    }
}