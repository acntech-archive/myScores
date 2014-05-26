package myscores.calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import myscores.domain.GameType;
import myscores.domain.Group;
import myscores.domain.Match;
import myscores.domain.Result;
import myscores.domain.Team;
import org.junit.Assert;
import org.junit.Test;

public class GroupStandingsCalculatorTest {
    
    @Test
    public void testFinishedGroup() {
        Team team1 = createTeam(1,"Brazil");
        Team team2 = createTeam(2,"Mexico");
        Team team3 = createTeam(3,"Croatia");
        Team team4 = createTeam(4,"Cameroon");        
        
        Match match1 = createMatch(1, team1, team3, GameType.GROUP_GAME, createResult(1, 1, 0));
        Match match2 = createMatch(2, team2, team4, GameType.GROUP_GAME, createResult(2, 2, 2));
        Match match16 = createMatch(16, team1, team2, GameType.GROUP_GAME, createResult(16, 1, 2));
        Match match19 = createMatch(19, team4, team3, GameType.GROUP_GAME, createResult(19, 1, 1));
        Match match35 = createMatch(35, team4, team1, GameType.GROUP_GAME, createResult(35, 2, 4));
        Match match36 = createMatch(36, team3, team2, GameType.GROUP_GAME, createResult(36, 0, 1));
        
        List<Team> teamsInGroup = createListOfTeams(team1,team2, team3, team4);
        List<Match> matchesInGroup = createListOfMatches(match1, match2, match16, match19, match35, match36);
        
        Group group = createGroup(1, "Group A", teamsInGroup, matchesInGroup);
        
        GroupStandingsCalculator calc = new GroupStandingsCalculator();
        HashMap<Integer, TeamGroupResult> groupStandings = calc.calculateGroupStanding(group);
        
        Assert.assertEquals("Antall poeng for; " + team1.getName(), 6, groupStandings.get(team1.getId()).getPoints());
        Assert.assertEquals("Antall poeng for; " + team2.getName(), 7, groupStandings.get(team2.getId()).getPoints());
        Assert.assertEquals("Antall poeng for; " + team3.getName(), 1, groupStandings.get(team3.getId()).getPoints());
        Assert.assertEquals("Antall poeng for; " + team4.getName(), 2, groupStandings.get(team4.getId()).getPoints());
        
        Assert.assertEquals("Antall mål for; " + team1.getName(), 6, groupStandings.get(team1.getId()).getGoalsScored());
        Assert.assertEquals("Antall mål for; " + team2.getName(), 5, groupStandings.get(team2.getId()).getGoalsScored());
        Assert.assertEquals("Antall mål for; " + team3.getName(), 1, groupStandings.get(team3.getId()).getGoalsScored());
        Assert.assertEquals("Antall mål for; " + team4.getName(), 5, groupStandings.get(team4.getId()).getGoalsScored());
        
        Assert.assertEquals("Antall baklengsmål for; " + team1.getName(), 4, groupStandings.get(team1.getId()).getGoalsAgaints());
        Assert.assertEquals("Antall baklengsmål for; " + team2.getName(), 3, groupStandings.get(team2.getId()).getGoalsAgaints());
        Assert.assertEquals("Antall baklengsmål for; " + team3.getName(), 3, groupStandings.get(team3.getId()).getGoalsAgaints());
        Assert.assertEquals("Antall baklengsmål for; " + team4.getName(), 7, groupStandings.get(team4.getId()).getGoalsAgaints());
    }
    
    private List<Match> createListOfMatches(Match... matches) {
        List<Match> listOfMatches = new ArrayList();
        for (Match match : matches) {
            listOfMatches.add(match);
        }
        return listOfMatches;
    }
    
    private List<Team> createListOfTeams(Team... teams) {
        List<Team> listOfTeams = new ArrayList();
        for (Team team : teams) {
            listOfTeams.add(team);
        }
        return listOfTeams;
    }
    
    private Result createResult(int id, int homeGoals, int awayGoals) {
        Result result = new Result();
        result.setId(id);
        result.setHomeGoals(homeGoals);
        result.setAwayGoals(awayGoals);
        return result;
    }
    
    private Group createGroup(int id, String groupName, List<Team> teamsInGroup, List<Match> matchesInGroup) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setId(id);
        group.setMatches(matchesInGroup);
        group.setTeams(teamsInGroup);
        return group;
    }
    
    private Match createMatch(int id, Team homeTeam, Team awayTeam, GameType gameType, Result result) {
        Match match = new Match();
        match.setId(id);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setGameType(gameType);
        match.setResult(result);
        return match;
    }
    
    private Team createTeam(int id, String name) {
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        return team;
    }
    
}
