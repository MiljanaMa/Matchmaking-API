package com.example.matchmaking.service;

import com.example.matchmaking.dto.MatchRequestDto;
import com.example.matchmaking.model.EloCalculator;
import com.example.matchmaking.model.Match;
import com.example.matchmaking.model.Player;
import com.example.matchmaking.model.Team;
import com.example.matchmaking.repository.MatchRepository;
import com.example.matchmaking.repository.PlayerRepository;
import com.example.matchmaking.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MatchService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Transactional
    public void create(MatchRequestDto match) throws Exception {
        Team team1 = getTeam(match.getTeam1Id());
        Team team2 = getTeam(match.getTeam2Id());

        validateWinningTeam(match, team1, team2);

        double team1AverageElo = EloCalculator.getTeamAverageElo(team1);
        double team2AverageElo = EloCalculator.getTeamAverageElo(team2);
        double team1Score = EloCalculator.getScoreRating(team1, match.getWinningTeamId());
        double team2Score = EloCalculator.getScoreRating(team2, match.getWinningTeamId());

        Team winningTeam = determineWinningTeam(team1, team2, team1Score);

        updatePlayerStats(team1, match, team2AverageElo, team1Score);
        updatePlayerStats(team2, match, team1AverageElo, team2Score);

        Match newMatch = new Match(team1, team2, winningTeam, match.getDuration());
        matchRepository.save(newMatch);
    }
    private Team determineWinningTeam(Team team1, Team team2, double team1Score) {
        if (team1Score == 0.5) {
            return null;
        }
        return team1Score == 1.0 ? team1 : team2;
    }
    private Team getTeam(String teamId) throws Exception {
        return teamRepository.findById(UUID.fromString(teamId))
                .orElseThrow(() -> new Exception("Team not found for ID: " + teamId));
    }
    private void validateWinningTeam(MatchRequestDto match, Team team1, Team team2) throws Exception {
        boolean isValid = match.getWinningTeamId() != null
                && (team1.getId().toString().equals(match.getWinningTeamId()) || team2.getId().toString().equals(match.getWinningTeamId()));

        if (!isValid) {
            throw new Exception("Winning team is not valid.");
        }
    }

    private void updatePlayerStats(Team team, MatchRequestDto match, double rivalAverageElo, double s) {
        for (Player player : team.getPlayers()) {
            player.setHoursPlayed(player.getHoursPlayed() + match.getDuration());

            if (s == 1)
                player.setWins(player.getWins() + 1);
            else if (s == 0)
                player.setLosses(player.getLosses() + 1);

            player.setElo(EloCalculator.calculateElo(player, rivalAverageElo, s));
            player.setRatingAdjustment(EloCalculator.determineRatingAdjustment(player.getHoursPlayed()));
        }
        playerRepository.saveAll(team.getPlayers());
    }
}
