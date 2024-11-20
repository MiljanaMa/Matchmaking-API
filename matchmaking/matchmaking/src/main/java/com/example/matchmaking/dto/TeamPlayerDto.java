package com.example.matchmaking.dto;

import com.example.matchmaking.mapper.DtoEntity;
import jakarta.validation.constraints.NotEmpty;

public class TeamPlayerDto implements DtoEntity {
    private String id;
    @NotEmpty(message = "Nickname is required")
    private String nickname;
    private int wins;
    private int losses;
    private double elo;
    private int hoursPlayed;
    private String teamId;
    private Integer ratingAdjustment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getElo() {
        return elo;
    }

    public void setElo(double elo) {
        this.elo = elo;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeam(String teamId) {
        this.teamId = teamId;
    }

    public Integer getRatingAdjustment() {
        return ratingAdjustment;
    }

    public void setRatingAdjustment(Integer ratingAdjustment) {
        this.ratingAdjustment = ratingAdjustment;
    }
}