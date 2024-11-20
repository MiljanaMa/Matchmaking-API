package com.example.matchmaking.service;

import com.example.matchmaking.dto.PlayerDto;
import com.example.matchmaking.dto.PlayerRequestDto;
import com.example.matchmaking.mapper.DtoUtils;
import com.example.matchmaking.model.Player;
import com.example.matchmaking.repository.PlayerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerDto create(PlayerRequestDto playerRequestDto) {
        Player player = new Player(playerRequestDto.getNickname());

        try{
            Player savedPlayer = playerRepository.save(player);
            return (PlayerDto) new DtoUtils().convertToDto(savedPlayer, new PlayerDto());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nickname must be unique", e);
        }
    }
    public PlayerDto getById(String id) {
        Player player = playerRepository.findById(UUID.fromString(id)).orElse(null);
        if (player != null)
            return (PlayerDto) new DtoUtils().convertToDto(player, new PlayerDto());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    }
    public Set<PlayerDto> getAll() {
        Set<Player> players = new HashSet(playerRepository.findAll());
        return (Set<PlayerDto>) new DtoUtils().convertToDtos(players, new PlayerDto());
    }
}
