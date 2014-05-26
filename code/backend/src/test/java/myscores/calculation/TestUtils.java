package myscores.calculation;

import java.util.ArrayList;
import java.util.List;
import myscores.domain.GameType;
import myscores.domain.Group;
import myscores.domain.Match;
import myscores.domain.Result;
import myscores.domain.Team;

public class TestUtils {
    public static List<Match> createListOfMatches(Match... matches) {
        List<Match> listOfMatches = new ArrayList();
        for (Match match : matches) {
            listOfMatches.add(match);
        }
        return listOfMatches;
    }
    
    public static List<Team> createListOfTeams(Team... teams) {
        List<Team> listOfTeams = new ArrayList();
        for (Team team : teams) {
            listOfTeams.add(team);
        }
        return listOfTeams;
    }
    
    public static Result createResult(int id, int homeGoals, int awayGoals) {
        Result result = new Result();
        result.setId(id);
        result.setHomeGoals(homeGoals);
        result.setAwayGoals(awayGoals);
        return result;
    }
    
    public static Group createGroup(int id, String groupName, List<Team> teamsInGroup, List<Match> matchesInGroup) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setId(id);
        group.setMatches(matchesInGroup);
        group.setTeams(teamsInGroup);
        return group;
    }
    
    public static Match createMatch(int id, Team homeTeam, Team awayTeam, GameType gameType, Result result) {
        Match match = new Match();
        match.setId(id);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setGameType(gameType);
        match.setResult(result);
        return match;
    }
    
    public static Team createTeam(int id, String name) {
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        return team;
    }
}
