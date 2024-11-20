package com.example.matchmaking.model;

import java.util.UUID;

public class EloCalculator {
    public static double calculateElo(Player player, double rivalAverageElo, double s) {
        double playerElo = player.getElo();
        double expectedElo = 1 / (1 + Math.pow(10, (rivalAverageElo - playerElo) / 400));
        int k = determineRatingAdjustment(player.getHoursPlayed());
        return playerElo + k * (s - expectedElo);
    }
    public static int determineRatingAdjustment(int hoursPlayed) {
        if (hoursPlayed < 500) {
            return 50;
        } else if (hoursPlayed < 1000) {
            return 40;
        } else if (hoursPlayed < 3000) {
            return 30;
        } else if (hoursPlayed < 5000) {

            return 20;
        } else {
            return 10;
        }
    }
    public static double getTeamAverageElo(Team team){
        double elo = 0;
        for(Player player: team.getPlayers()){
            elo += player.getElo();
        }
        return elo/5;
    }
    public static double getScoreRating(Team team, String winningTeamId){
        if(winningTeamId == null)
            return 0.5;
        else if(team.getId().equals(UUID.fromString(winningTeamId)))
            return 1;
        return 0;
    }
}
