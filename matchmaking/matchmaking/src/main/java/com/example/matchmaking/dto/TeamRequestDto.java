package com.example.matchmaking.dto;

import com.example.matchmaking.mapper.DtoEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class TeamRequestDto implements DtoEntity {
    @NotEmpty(message = "Team name is required")
    private String teamName;
    @Size(min = 5, max = 5, message = "Must be exactly 5 players")
    private Set<String> players = new HashSet<>();

    public Set<String> getPlayers() {
        return players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }
}
