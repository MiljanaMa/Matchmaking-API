package com.example.matchmaking.dto;

import com.example.matchmaking.mapper.DtoEntity;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class TeamDto implements DtoEntity {
    private String id;
    @NotEmpty(message = "Team name is required")
    private String teamName;
    private Set<TeamPlayerDto> players = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<TeamPlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(Set<TeamPlayerDto> players) {
        this.players = players;
    }
}
