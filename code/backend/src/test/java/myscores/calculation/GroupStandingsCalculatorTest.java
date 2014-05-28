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
        Team brazil = TestUtils.createTeam(1,"Brazil");
        Team mexico = TestUtils.createTeam(2,"Mexico");
        Team croatia = TestUtils.createTeam(3,"Croatia");
        Team cameroon = TestUtils.createTeam(4,"Cameroon");        
        
        Match match1 = TestUtils.createMatch(1, brazil, croatia, GameType.GROUP_GAME, TestUtils.createResult(1, 1, 0));
        Match match2 = TestUtils.createMatch(2, mexico, cameroon, GameType.GROUP_GAME, TestUtils.createResult(2, 2, 2));
        Match match16 = TestUtils.createMatch(16, brazil, mexico, GameType.GROUP_GAME, TestUtils.createResult(16, 1, 2));
        Match match19 = TestUtils.createMatch(19, cameroon, croatia, GameType.GROUP_GAME, TestUtils.createResult(19, 1, 1));
        Match match35 = TestUtils.createMatch(35, cameroon, brazil, GameType.GROUP_GAME, TestUtils.createResult(35, 2, 4));
        Match match36 = TestUtils.createMatch(36, croatia, mexico, GameType.GROUP_GAME, TestUtils.createResult(36, 0, 1));
        
        List<Team> teamsInGroup = TestUtils.createListOfTeams(brazil,mexico, croatia, cameroon);
        List<Match> matchesInGroup = TestUtils.createListOfMatches(match1, match2, match16, match19, match35, match36);
        
        Group group = TestUtils.createGroup(1, "Group A", teamsInGroup, matchesInGroup);
        
        GroupStandingsCalculator calc = new GroupStandingsCalculator();
        HashMap<Integer, TeamGroupResult> groupStandings = calc.calculateGroupStanding(group);
        
        Assert.assertEquals("Antall poeng for; " + brazil.getName(), 6, groupStandings.get(brazil.getId()).getPoints());
        Assert.assertEquals("Antall poeng for; " + mexico.getName(), 7, groupStandings.get(mexico.getId()).getPoints());
        Assert.assertEquals("Antall poeng for; " + croatia.getName(), 1, groupStandings.get(croatia.getId()).getPoints());
        Assert.assertEquals("Antall poeng for; " + cameroon.getName(), 2, groupStandings.get(cameroon.getId()).getPoints());
        
        Assert.assertEquals("Antall mål for; " + brazil.getName(), 6, groupStandings.get(brazil.getId()).getGoalsScored());
        Assert.assertEquals("Antall mål for; " + mexico.getName(), 5, groupStandings.get(mexico.getId()).getGoalsScored());
        Assert.assertEquals("Antall mål for; " + croatia.getName(), 1, groupStandings.get(croatia.getId()).getGoalsScored());
        Assert.assertEquals("Antall mål for; " + cameroon.getName(), 5, groupStandings.get(cameroon.getId()).getGoalsScored());
        
        Assert.assertEquals("Antall baklengsmål for; " + brazil.getName(), 4, groupStandings.get(brazil.getId()).getGoalsAgaints());
        Assert.assertEquals("Antall baklengsmål for; " + mexico.getName(), 3, groupStandings.get(mexico.getId()).getGoalsAgaints());
        Assert.assertEquals("Antall baklengsmål for; " + croatia.getName(), 3, groupStandings.get(croatia.getId()).getGoalsAgaints());
        Assert.assertEquals("Antall baklengsmål for; " + cameroon.getName(), 7, groupStandings.get(cameroon.getId()).getGoalsAgaints());
    }  
}