package myscores.domain;

public class Result {
    
    private int id;
    private int homeGoals;
    private int awayGoals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }
    
    public Winner getWinner(){
        if(homeGoals == awayGoals){
            return Winner.DRAW;
        } else if( homeGoals > awayGoals ){
            return Winner.HOMETEAM;
        }else{
            return Winner.AWAYTEAM;
        }    
    }
    
}
