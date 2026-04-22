package com.example.demo.controller;

import com.example.demo.model.Club;
import com.example.demo.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<Club>> getAll() {
        return ResponseEntity.ok(clubService.findAll());
    }

    @PostMapping
    public ResponseEntity<Club> create(@RequestBody Club club) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.save(club));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clubService.delete(id);
        return ResponseEntity.noContent().build();
    }
}