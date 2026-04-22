package com.example.demo.controller;

import com.example.demo.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ClubService clubService;

    @GetMapping("/")
    public String index(Model model) {
        // Кладём список клубов в модель, чтобы Thymeleaf их отрисовал
        model.addAttribute("clubs", clubService.findAll());
        return "index";
    }
}