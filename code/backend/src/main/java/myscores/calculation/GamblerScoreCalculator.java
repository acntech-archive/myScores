package myscores.calculation;

import java.util.List;
import myscores.domain.Gambler;
import myscores.domain.GameType;
import myscores.domain.Match;

public class GamblerScoreCalculator {
    
    private static final int GROUP_GAME_WINNER_SCORE = 2;
    private static final int GROUP_GAME_RESULT_SCORE = 2;

    public int calculate(Gambler gambler, List<Match> correctMatchResults) {

        int gamblerScore = 0;
        
        for (Match correctMatch : correctMatchResults) {
            gamblerScore += calculateMatchScore(findGamblerMatch(gambler, correctMatch.getId()), correctMatch);
        }

        return gamblerScore;
    }
    
    private int calculateMatchScore(Match gamblerMatch, Match correctMatch ){
        
        if(correctMatch.getGameType() == GameType.GROUP_GAME){
            return calculateGroupGameScore(gamblerMatch, correctMatch);
        }
        return 0;
    }
    
    private int calculateGroupGameScore(Match gamblerMatch, Match correctMatch){
        
        int groupGameScore = 0;
        if(isCorrectWinner(gamblerMatch, correctMatch)){
            groupGameScore += GROUP_GAME_WINNER_SCORE;
        }
        if(isCorrectResult(gamblerMatch, correctMatch)){
            groupGameScore += GROUP_GAME_RESULT_SCORE;
        }
        return groupGameScore;
    }
    
    private boolean isCorrectWinner(Match gamblerMatch, Match correctMatch){
        return gamblerMatch.getResult().getWinner() == correctMatch.getResult().getWinner();
    }
    
    private boolean isCorrectResult(Match gamblerMatch, Match correctMatch){
        return gamblerMatch.getResult().getHomeGoals() == correctMatch.getResult().getHomeGoals() && gamblerMatch.getResult().getAwayGoals() == correctMatch.getResult().getAwayGoals();
    }

    private Match findGamblerMatch(Gambler gambler, int matchId) {
        for (Match match : gambler.getMatches()) {
            if (match.getId() == matchId) {
                return match;
            }
        }
        throw new MatchNotFoundException("Match #" + matchId + " not found for gambler " + gambler.getName());
    }

}
