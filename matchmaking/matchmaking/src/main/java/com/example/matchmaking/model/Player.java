package com.example.matchmaking.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "players", uniqueConstraints = @UniqueConstraint(columnNames = "nickname"))
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(name = "wins")
    private int wins;
    @Column(name = "losses")
    private int losses;
    @Column(name = "elo")
    private double elo;
    @Column(name = "hours_played")
    private int hoursPlayed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;
    @Column(name = "rating_adjustment", nullable = true)
    private Integer ratingAdjustment;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public Player() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getRatingAdjustment() {
        return ratingAdjustment;
    }

    public void setRatingAdjustment(Integer ratingAdjustment) {
        this.ratingAdjustment = ratingAdjustment;
    }
}
