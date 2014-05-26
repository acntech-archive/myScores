package myscores.calculation;

import java.util.HashMap;
import java.util.List;
import myscores.domain.Gambler;
import myscores.domain.Group;
import myscores.domain.Match;
import myscores.domain.Result;
import myscores.domain.Team;
import myscores.domain.Winner;

public class GroupStandingsCalculator {
    
    HashMap<Integer, TeamGroupResult> groupStanding;
    
    public HashMap<Integer, TeamGroupResult> calculateGroupStanding(Group group) {
        
        List<Match> groupMatches = group.getMatches();
        List<Team> groupTeams = group.getTeams();        
        groupStanding = new HashMap();
        
        for (Team team : groupTeams ) {
            TeamGroupResult teamGroupResult = new TeamGroupResult(team.getId());
            groupStanding.put(team.getId(), teamGroupResult);
        }
        
        for (Match match : groupMatches ) {
            calculateMatchScores(match);
        }
        return groupStanding;
    }
    
    private void calculateMatchScores(Match match) {
        Result result = match.getResult();
            
        //homeTeam
        TeamGroupResult homeTeam = groupStanding.get(match.getHomeTeam().getId());
        homeTeam.goalsAgainst += result.getAwayGoals();
        homeTeam.goalsScored += result.getHomeGoals();
            
        //awayTeam
        TeamGroupResult awayTeam = groupStanding.get(match.getAwayTeam().getId());
        awayTeam.goalsScored += result.getAwayGoals();
        awayTeam.goalsAgainst += result.getHomeGoals();
            
        //awardPoints
        if(result.getWinner().equals(Winner.HOMETEAM)) {
            homeTeam.points += 3;
        } else if(result.getWinner().equals(Winner.AWAYTEAM)) {
            awayTeam.points += 3;
        } else if(result.getWinner().equals(Winner.DRAW)) {
            homeTeam.points += 1;
            awayTeam.points += 1;
        }
            
        groupStanding.put(homeTeam.teamId, homeTeam);
        groupStanding.put(awayTeam.teamId, awayTeam);
    }    
       
    
    
}
