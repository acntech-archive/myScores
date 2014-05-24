package myscores.calculation;

import java.util.ArrayList;
import java.util.List;
import myscores.domain.Gambler;
import myscores.domain.GameType;
import myscores.domain.Match;
import myscores.domain.Result;
import org.junit.Assert;
import org.junit.Test;

public class GamblerScoreCalculatorTest {
    
    /*
    @Test
    public void testSimpleCalculation(){
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Enkel utregning av spillerpoeng feilet", 0, calc.calculate());
    }*/
    
    @Test
    public void testOneGroupMatchCorrectScoreCorrectWinner(){
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match match = new Match();
        Result result = new Result();
        result.setHomeGoals(1);
        result.setAwayGoals(1);
        match.setResult(result);
        match.setGameType(GameType.GROUP_GAME);
        gamblerMatches.add(match);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        correctMatchResults.add(match);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Enkel utregning av spillerpoeng ved korrekt vinner og korrekt resultat", 4, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testOneGroupMatchWrongScoreCorrectWinner(){
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        Result betResult = new Result();
        betResult.setHomeGoals(1);
        betResult.setAwayGoals(0);
        betMatch.setResult(betResult);
        betMatch.setGameType(GameType.GROUP_GAME);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        Result correctResult = new Result();
        correctResult.setHomeGoals(2);
        correctResult.setAwayGoals(0);
        correctMatch.setResult(correctResult);
        correctMatch.setGameType(GameType.GROUP_GAME);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Enkel utregning av spillerpoeng ved korrekt vinner og feil resultat", 2, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testOneGroupMatchWrongScoreWrongWinner(){
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        Result betResult = new Result();
        betResult.setHomeGoals(1);
        betResult.setAwayGoals(0);
        betMatch.setResult(betResult);
        betMatch.setGameType(GameType.GROUP_GAME);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        Result correctResult = new Result();
        correctResult.setHomeGoals(0);
        correctResult.setAwayGoals(0);
        correctMatch.setResult(correctResult);
        correctMatch.setGameType(GameType.GROUP_GAME);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Enkel utregning av spillerpoeng ved korrekt vinner og feil resultat", 0, calc.calculate(gambler, correctMatchResults));
    }
}
