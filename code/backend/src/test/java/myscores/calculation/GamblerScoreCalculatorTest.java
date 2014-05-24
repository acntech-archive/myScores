package myscores.calculation;

import java.util.ArrayList;
import java.util.List;
import myscores.domain.Gambler;
import myscores.domain.GameType;
import myscores.domain.Match;
import myscores.domain.Result;
import myscores.domain.Team;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class GamblerScoreCalculatorTest {
    
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
    
    @Test
    public void testOneOfTwoCorrectTeamsInRoundOfSixteen(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.ROUND_OF_SIXTEEN);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.ROUND_OF_SIXTEEN);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i RoundOfSixteen", 4, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInRoundOfSixteen(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.ROUND_OF_SIXTEEN);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.ROUND_OF_SIXTEEN);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i RoundOfSixteen", 8, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInRoundOfSixteenOppositeDirection(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.ROUND_OF_SIXTEEN);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.ROUND_OF_SIXTEEN);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i RoundOfSixteen", 8, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testOneOfTwoCorrectTeamsInQuarterFinal(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.QUARTER_FINALS);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.QUARTER_FINALS);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i QuarterFinal", 6, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInQuarterFinal(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.QUARTER_FINALS);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.QUARTER_FINALS);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i QuarterFinal", 12, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInQuarterFinalOppositeDirection(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.QUARTER_FINALS);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.QUARTER_FINALS);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i QuarterFinal", 12, calc.calculate(gambler, correctMatchResults));
    }
    
    
    @Test
    public void testOneOfTwoCorrectTeamsInSemiFinal(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.SEMI_FINALS);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.SEMI_FINALS);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i Semifinalen", 8, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInSemiFinal(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.SEMI_FINALS);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.SEMI_FINALS);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i Semifinalen", 16, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInQuarterSemiFinalOppositeDirection(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setGameType(GameType.SEMI_FINALS);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setGameType(GameType.SEMI_FINALS);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i Semifinalen", 16, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testOneOfTwoCorrectTeamsInBronzeFinal(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        Team firstTeamNotInFinal = new Team();
        secondTeamInFinal.setId(3);
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(firstTeamNotInFinal);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        betMatch.setResult(finalResult);
        betMatch.setGameType(GameType.BRONZE_FINAL);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setResult(finalResult);
        correctMatch.setGameType(GameType.BRONZE_FINAL);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i BronseFinalen", 10, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInBronzeFinalCorrectWinner(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(secondTeamInFinal);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        betMatch.setResult(finalResult);
        betMatch.setGameType(GameType.BRONZE_FINAL);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setResult(finalResult);
        correctMatch.setGameType(GameType.BRONZE_FINAL);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i BronseFinalen", 34, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInBronzeFinalOppositeDirectionCorrectWinner(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setAwayTeam(secondTeamInFinal);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        betMatch.setResult(finalResult);
        betMatch.setGameType(GameType.BRONZE_FINAL);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setResult(finalResult);
        correctMatch.setGameType(GameType.BRONZE_FINAL);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i BronseFinalen", 34, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testOneOfTwoCorrectTeamsInFinal(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        Team firstTeamNotInFinal = new Team();
        secondTeamInFinal.setId(3);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(firstTeamNotInFinal);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        betMatch.setResult(finalResult);
        betMatch.setGameType(GameType.FINAL);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setResult(finalResult);
        correctMatch.setGameType(GameType.FINAL);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i Finalen", 12, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInFinalCorrectWinner(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setAwayTeam(secondTeamInFinal);
        betMatch.setHomeTeam(secondTeamInFinal);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        betMatch.setResult(finalResult);
        betMatch.setGameType(GameType.FINAL);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setResult(finalResult);
        correctMatch.setGameType(GameType.FINAL);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i Finalen", 40, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testTwoOfTwoCorrectTeamsInFinalOppositeDirectionCorrectWinner(){
        
        Team firstTeamInFinal = new Team();
        firstTeamInFinal.setId(1);
        
        Team secondTeamInFinal = new Team();
        secondTeamInFinal.setId(2);
        
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = new ArrayList<>();
        Match betMatch = new Match();
        betMatch.setHomeTeam(secondTeamInFinal);
        betMatch.setAwayTeam(secondTeamInFinal);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        betMatch.setResult(finalResult);
        betMatch.setGameType(GameType.FINAL);
        gamblerMatches.add(betMatch);
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = new ArrayList<>();
        Match correctMatch = new Match();
        correctMatch.setAwayTeam(firstTeamInFinal);
        correctMatch.setHomeTeam(secondTeamInFinal);
        correctMatch.setResult(finalResult);
        correctMatch.setGameType(GameType.FINAL);
        correctMatchResults.add(correctMatch);
        
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved et av to korrekte lag i Finalen", 40, calc.calculate(gambler, correctMatchResults));
    }
    
    @Test
    public void testCompleteRoundOfFinals(){
        
        Gambler gambler = new Gambler();
        List<Match> gamblerMatches = createCompleteMatchSetForFinals();
        gambler.setMatches(gamblerMatches);
        
        List<Match> correctMatchResults = createCompleteMatchSetForFinals();
        
        GamblerScoreCalculator calc = new GamblerScoreCalculator();
        Assert.assertEquals("Utregning av spillerpoeng ved alle riktige lag i Round of Sixteen", 218, calc.calculate(gambler, correctMatchResults));
    }
    
    
    
    private List<Match> createCompleteMatchSetForFinals(){
        
        Team team1 = new Team();
        team1.setId(1);
        Team team2 = new Team();
        team2.setId(2);
        Team team3 = new Team();
        team3.setId(3);
        Team team4 = new Team();
        team4.setId(4);
        Team team5 = new Team();
        team5.setId(5);
        Team team6 = new Team();
        team6.setId(6);
        Team team7 = new Team();
        team7.setId(7);
        Team team8 = new Team();
        team8.setId(8);
        Team team9 = new Team();
        team9.setId(9);
        Team team10 = new Team();
        team10.setId(10);
        Team team11 = new Team();
        team11.setId(11);
        Team team12 = new Team();
        team12.setId(12);
        Team team13 = new Team();
        team13.setId(13);
        Team team14 = new Team();
        team14.setId(14);
        Team team15 = new Team();
        team15.setId(15);
        Team team16 = new Team();
        team16.setId(16);
        
        Result tempResult = new Result();
        tempResult.setHomeGoals(1);
        tempResult.setAwayGoals(0);
                
        
        List<Match> completeSetOfMatches = new ArrayList<>();
        
        Match sixteenMatch1 = new Match();
        sixteenMatch1.setId(1);
        sixteenMatch1.setHomeTeam(team1);
        sixteenMatch1.setAwayTeam(team2);
        sixteenMatch1.setResult(tempResult);
        sixteenMatch1.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch1);
        
        Match sixteenMatch2 = new Match();
        sixteenMatch2.setId(2);
        sixteenMatch2.setHomeTeam(team3);
        sixteenMatch2.setAwayTeam(team4);
        sixteenMatch2.setResult(tempResult);
        sixteenMatch2.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch2);
        
        Match sixteenMatch3 = new Match();
        sixteenMatch3.setId(3);
        sixteenMatch3.setHomeTeam(team5);
        sixteenMatch3.setAwayTeam(team6);
        sixteenMatch3.setResult(tempResult);
        sixteenMatch3.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch3);
        
        Match sixteenMatch4 = new Match();
        sixteenMatch4.setId(4);
        sixteenMatch4.setHomeTeam(team7);
        sixteenMatch4.setAwayTeam(team8);
        sixteenMatch4.setResult(tempResult);
        sixteenMatch4.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch4);
        
        Match sixteenMatch5 = new Match();
        sixteenMatch5.setId(5);
        sixteenMatch5.setHomeTeam(team9);
        sixteenMatch5.setAwayTeam(team10);
        sixteenMatch5.setResult(tempResult);
        sixteenMatch5.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch5);
        
        Match sixteenMatch6 = new Match();
        sixteenMatch6.setId(6);
        sixteenMatch6.setHomeTeam(team11);
        sixteenMatch6.setAwayTeam(team12);
        sixteenMatch6.setResult(tempResult);
        sixteenMatch6.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch6);
        
        Match sixteenMatch7 = new Match();
        sixteenMatch7.setId(7);
        sixteenMatch7.setHomeTeam(team13);
        sixteenMatch7.setAwayTeam(team14);
        sixteenMatch7.setResult(tempResult);
        sixteenMatch7.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch7);
        
        Match sixteenMatch8 = new Match();
        sixteenMatch8.setId(8);
        sixteenMatch8.setHomeTeam(team15);
        sixteenMatch8.setAwayTeam(team16);
        sixteenMatch8.setResult(tempResult);
        sixteenMatch8.setGameType(GameType.ROUND_OF_SIXTEEN);
        completeSetOfMatches.add(sixteenMatch8);
        
        
        Match quarterFinalMatch1 = new Match();
        quarterFinalMatch1.setId(9);
        quarterFinalMatch1.setHomeTeam(team1);
        quarterFinalMatch1.setAwayTeam(team2);
        quarterFinalMatch1.setResult(tempResult);
        quarterFinalMatch1.setGameType(GameType.QUARTER_FINALS);
        completeSetOfMatches.add(quarterFinalMatch1);
        
        Match quarterFinalMatch2 = new Match();
        quarterFinalMatch2.setId(10);
        quarterFinalMatch2.setHomeTeam(team3);
        quarterFinalMatch2.setAwayTeam(team4);
        quarterFinalMatch2.setResult(tempResult);
        quarterFinalMatch2.setGameType(GameType.QUARTER_FINALS);
        completeSetOfMatches.add(quarterFinalMatch2);
        
        Match quarterFinalMatch3 = new Match();
        quarterFinalMatch3.setId(11);
        quarterFinalMatch3.setHomeTeam(team5);
        quarterFinalMatch3.setAwayTeam(team6);
        quarterFinalMatch3.setResult(tempResult);
        quarterFinalMatch3.setGameType(GameType.QUARTER_FINALS);
        completeSetOfMatches.add(quarterFinalMatch3);
        
        Match quarterFinalMatch4 = new Match();
        quarterFinalMatch4.setId(12);
        quarterFinalMatch4.setHomeTeam(team7);
        quarterFinalMatch4.setAwayTeam(team8);
        quarterFinalMatch4.setResult(tempResult);
        quarterFinalMatch4.setGameType(GameType.QUARTER_FINALS);
        completeSetOfMatches.add(quarterFinalMatch4);
        
        Match semiFinalMatch1 = new Match();
        semiFinalMatch1.setId(13);
        semiFinalMatch1.setHomeTeam(team1);
        semiFinalMatch1.setAwayTeam(team2);
        semiFinalMatch1.setResult(tempResult);
        semiFinalMatch1.setGameType(GameType.SEMI_FINALS);
        completeSetOfMatches.add(semiFinalMatch1);
        
        Match semiFinalMatch2 = new Match();
        semiFinalMatch2.setId(14);
        semiFinalMatch2.setHomeTeam(team3);
        semiFinalMatch2.setAwayTeam(team4);
        semiFinalMatch2.setResult(tempResult);
        semiFinalMatch2.setGameType(GameType.SEMI_FINALS);
        completeSetOfMatches.add(semiFinalMatch2);
        
        Match bronzeFinalMatch = new Match();
        bronzeFinalMatch.setId(15);
        bronzeFinalMatch.setHomeTeam(team1);
        bronzeFinalMatch.setAwayTeam(team2);
        Result bronzeFinalResult = new Result();
        bronzeFinalResult.setHomeGoals(2);
        bronzeFinalResult.setAwayGoals(0);
        bronzeFinalMatch.setResult(bronzeFinalResult);
        bronzeFinalMatch.setGameType(GameType.BRONZE_FINAL);
        completeSetOfMatches.add(bronzeFinalMatch);
        
        Match finalMatch = new Match();
        finalMatch.setId(16);
        finalMatch.setHomeTeam(team3);
        finalMatch.setAwayTeam(team4);
        Result finalResult = new Result();
        finalResult.setHomeGoals(2);
        finalResult.setAwayGoals(0);
        finalMatch.setResult(finalResult);
        finalMatch.setGameType(GameType.FINAL);
        completeSetOfMatches.add(finalMatch);
        
        return completeSetOfMatches;
    }
}
