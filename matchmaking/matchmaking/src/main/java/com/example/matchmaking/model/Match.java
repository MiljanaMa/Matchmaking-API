package com.example.matchmaking.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
    @Column(name = "team1_id", nullable = false)
    private UUID team1Id;
    @Column(name = "team2_id", nullable = false)
    private UUID team2Id;
    @Column(name = "winning_team", nullable = true)
    private UUID winningTeam;
    @Column(name = "duration", nullable = false)
    private int duration;

    public Match() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(UUID team1Id) {
        this.team1Id = team1Id;
    }

    public UUID getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(UUID team2Id) {
        this.team2Id = team2Id;
    }

    public UUID getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(UUID winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
