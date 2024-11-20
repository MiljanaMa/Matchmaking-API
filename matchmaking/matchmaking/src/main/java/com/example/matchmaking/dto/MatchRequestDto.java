package com.example.matchmaking.dto;

import com.example.matchmaking.mapper.DtoEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class MatchRequestDto implements DtoEntity {
    @NotEmpty(message = "Team 1 id is required")
    private String team1Id;
    @NotEmpty(message = "Team 2 id is required")
    private String team2Id;
    private String winningTeamId;
    @NotEmpty(message = "Duration is required.")
    @Min(value = 1, message = "Duration must be greater than 1")
    private Integer duration;

    public String getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    public String getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(String winningTeamId) {
        this.winningTeamId = winningTeamId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
