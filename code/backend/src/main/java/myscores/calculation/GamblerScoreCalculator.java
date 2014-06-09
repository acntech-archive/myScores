package myscores.calculation;

import myscores.domain.Gambler;
import myscores.domain.GameType;
import myscores.domain.Match;
import myscores.domain.Team;
import myscores.domain.Winner;

import java.util.ArrayList;
import java.util.List;

public class GamblerScoreCalculator {

    private static final int GROUP_GAME_WINNER_SCORE = 2;
    private static final int GROUP_GAME_RESULT_SCORE = 2;
    private static final int TEAM_IN_ROUND_OF_SIXTEEN_SCORE = 4;
    private static final int TEAM_IN_QUARTER_FINAL_SCORE = 6;
    private static final int TEAM_IN_SEMI_FINAL_SCORE = 8;
    private static final int TEAM_IN_BRONZE_FINAL_SCORE = 10;
    private static final int TEAM_IN_FINAL_SCORE = 12;
    private static final int TEAM_IN_BRONZE_SCORE = 14;
    private static final int TEAM_IN_GOLD_SCORE = 16;

    public int calculate(Gambler gambler, List<Match> correctMatchResults) {

        int gamblerScore = 0;

        for (Match correctMatch : correctMatchResults) {
            if (correctMatch.getGameType() == GameType.GROUP_GAME) {
                gamblerScore += calculateGroupGameScore(findGamblerMatch(gambler, correctMatch.getId()), correctMatch);
            } else if (correctMatch.getGameType() == GameType.BRONZE_FINAL) {
                gamblerScore += calculateBronzeWinnerScore(findGamblerMatch(gambler, correctMatch.getId()), correctMatch);
            } else if (correctMatch.getGameType() == GameType.FINAL) {
                gamblerScore += calculateWinnerScore(findGamblerMatch(gambler, correctMatch.getId()), correctMatch);
            }
        }

        gamblerScore += calculateRoundOfSixteenScore(gambler, correctMatchResults);
        gamblerScore += calculateQuarterFinalScore(gambler, correctMatchResults);
        gamblerScore += calculateSemiFinalScore(gambler, correctMatchResults);
        gamblerScore += calculateBronzeFinalScore(gambler, correctMatchResults);
        gamblerScore += calculateFinalScore(gambler, correctMatchResults);

        return gamblerScore;
    }

    private int calculateRoundOfSixteenScore(Gambler gambler, List<Match> correctMatchResults) {

        return calculateTeamsInRound(gambler, correctMatchResults, GameType.ROUND_OF_SIXTEEN) * TEAM_IN_ROUND_OF_SIXTEEN_SCORE;
    }

    private int calculateQuarterFinalScore(Gambler gambler, List<Match> correctMatchResults) {

        return calculateTeamsInRound(gambler, correctMatchResults, GameType.QUARTER_FINALS) * TEAM_IN_QUARTER_FINAL_SCORE;
    }

    private int calculateSemiFinalScore(Gambler gambler, List<Match> correctMatchResults) {

        return calculateTeamsInRound(gambler, correctMatchResults, GameType.SEMI_FINALS) * TEAM_IN_SEMI_FINAL_SCORE;
    }

    private int calculateBronzeFinalScore(Gambler gambler, List<Match> correctMatchResults) {

        return calculateTeamsInRound(gambler, correctMatchResults, GameType.BRONZE_FINAL) * TEAM_IN_BRONZE_FINAL_SCORE;
    }

    private int calculateFinalScore(Gambler gambler, List<Match> correctMatchResults) {

        return calculateTeamsInRound(gambler, correctMatchResults, GameType.FINAL) * TEAM_IN_FINAL_SCORE;
    }

    private int calculateTeamsInRound(Gambler gambler, List<Match> correctMatchResults, GameType gameType) {

        List<Team> teamsInRound = new ArrayList<>();
        for (Match match : correctMatchResults) {
            if (match.getGameType() == gameType) {
                if (match.getHomeTeam() != null) {
                    teamsInRound.add(match.getHomeTeam());
                }
                if (match.getAwayTeam() != null) {
                    teamsInRound.add(match.getAwayTeam());
                }
            }
        }

        List<Team> gamblersTeamsInRound = new ArrayList<>();
        for (Match match : gambler.getMatches()) {
            if (match.getGameType() == gameType) {
                if (match.getHomeTeam() != null) {
                    gamblersTeamsInRound.add(match.getHomeTeam());
                }
                if (match.getAwayTeam() != null) {
                    gamblersTeamsInRound.add(match.getAwayTeam());
                }
            }
        }

        int calculatedTeamsInRound = 0;

        for (Team team : gamblersTeamsInRound) {
            if (teamExistsInList(team.getId(), teamsInRound)) {
                calculatedTeamsInRound++;
            }
        }

        return calculatedTeamsInRound;
    }

    private boolean teamExistsInList(int teamId, List<Team> teamsInRound) {
        for (Team team : teamsInRound) {
            if (team.getId() == teamId) {
                return true;
            }
        }
        return false;
    }

    private int calculateGroupGameScore(Match gamblerMatch, Match correctMatch) {

        int groupGameScore = 0;

        if (gamblerMatch.getGameType() == GameType.GROUP_GAME && correctMatch.getGameType() == GameType.GROUP_GAME) {
            if (isCorrectWinner(gamblerMatch, correctMatch)) {
                groupGameScore += GROUP_GAME_WINNER_SCORE;
            }
            if (isCorrectResult(gamblerMatch, correctMatch)) {
                groupGameScore += GROUP_GAME_RESULT_SCORE;
            }
        }
        return groupGameScore;
    }

    private int calculateBronzeWinnerScore(Match gamblerMatch, Match correctMatch) {

        Team winnerTeamGambler = findWinnerTeam(gamblerMatch);
        Team correctWinnerTeam = findWinnerTeam(correctMatch);

        if (winnerTeamGambler.getId() == correctWinnerTeam.getId()) {
            return TEAM_IN_BRONZE_SCORE;
        }
        return 0;
    }

    private int calculateWinnerScore(Match gamblerMatch, Match correctMatch) {

        Team winnerTeamGambler = findWinnerTeam(gamblerMatch);
        Team correctWinnerTeam = findWinnerTeam(correctMatch);

        if (winnerTeamGambler.getId() == correctWinnerTeam.getId()) {
            return TEAM_IN_GOLD_SCORE;
        }
        return 0;
    }

    private Team findWinnerTeam(Match match) {
        if (match.getResult().getWinner() == Winner.HOMETEAM) {
            return match.getHomeTeam();
        } else {
            return match.getAwayTeam();
        }
    }

    private boolean isCorrectWinner(Match gamblerMatch, Match correctMatch) {
        return gamblerMatch.getResult().getWinner() == correctMatch.getResult().getWinner();
    }

    private boolean isCorrectResult(Match gamblerMatch, Match correctMatch) {
        return gamblerMatch.getResult().getHomeGoals() == correctMatch.getResult().getHomeGoals() && gamblerMatch.getResult().getAwayGoals() == correctMatch.getResult().getAwayGoals();
    }

    private Match findGamblerMatch(Gambler gambler, int matchId) {
        for (Match match : gambler.getMatches()) {
            if (match.getId() == matchId) {
                return match;
            }
        }
        throw new MatchNotFoundException("Match #" + matchId + " not found for gambler " + gambler.getUsername());
    }

}
