package com.example.matchmaking.controller;
import com.example.matchmaking.dto.TeamDto;
import com.example.matchmaking.dto.TeamRequestDto;
import com.example.matchmaking.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> create(@Valid @RequestBody TeamRequestDto request) {
        try {
            TeamDto dto = teamService.create(request);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getById(@PathVariable String id) {
        try {
            TeamDto teamDto = teamService.getById(id);
            return ResponseEntity.ok(teamDto);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
