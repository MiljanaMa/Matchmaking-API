package com.example.matchmaking.controller;

import com.example.matchmaking.dto.PlayerDto;
import com.example.matchmaking.dto.PlayerRequestDto;
import com.example.matchmaking.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<PlayerDto> create(@Valid @RequestBody PlayerRequestDto playerRequestDto) {
        try {
            PlayerDto dto = playerService.create(playerRequestDto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getById(@PathVariable String id) {
        try {
            PlayerDto playerDto = playerService.getById(id);
            return ResponseEntity.ok(playerDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping()
    public ResponseEntity<Set<PlayerDto>> getAll() {
            return ResponseEntity.ok(playerService.getAll());
    }
}
