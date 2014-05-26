package myscores.calculation;

public class TeamGroupResult {

    int goalsAgainst;
    int goalsScored;
    int points;
    int teamId;

    public TeamGroupResult(int teamId) {
        this.teamId = teamId;
        goalsAgainst = 0;
        goalsScored = 0;
        points = 0;
    }

    public void increaseGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst += goalsAgainst;
    }
        
    public void increaseGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }
        
     public void increasePoints(int points) {
        this.points += points;
    }
        
    public int getGoalsAgaints() {
        return goalsAgainst;
    }
        
    public int getGoalsScored() {
        return goalsScored;
    }
        
    public int getPoints() {
        return points;
    }
}
