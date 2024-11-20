package com.example.matchmaking.service;

import com.example.matchmaking.dto.TeamDto;
import com.example.matchmaking.dto.TeamRequestDto;
import com.example.matchmaking.mapper.DtoUtils;
import com.example.matchmaking.model.Player;
import com.example.matchmaking.model.Team;
import com.example.matchmaking.repository.PlayerRepository;
import com.example.matchmaking.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    public TeamDto create(TeamRequestDto teamDto) {
        Set<Player> players = new HashSet<>();
        for (String playerId : teamDto.getPlayers()) {
            Player player = playerRepository.findById(UUID.fromString(playerId))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

            if (player.getTeam() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player is already in a team");
            }
            players.add(player);
        }

        Team team = new Team();
        team.createTeam(teamDto.getTeamName(), players);
        Team savedTeam = teamRepository.save(team);

        for (Player player : players) {
            player.setTeam(savedTeam);
        }
        playerRepository.saveAll(players);
        return (TeamDto) new DtoUtils().convertToDto(savedTeam, new TeamDto());
    }
    public TeamDto getById(String id) {
        Team team = teamRepository.findById(UUID.fromString(id)).orElse(null);
        if (team != null)
            return (TeamDto) new DtoUtils().convertToDto(team, new TeamDto());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
    }
}
