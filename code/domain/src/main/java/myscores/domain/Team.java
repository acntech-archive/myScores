package myscores.domain;

public class Team {

    private int id;
    private String name;
    private int fifaRanking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getFifaRanking() {
        return fifaRanking;
    }
    
    public void setFifaRanking(int fifaRanking) {
        this.fifaRanking = fifaRanking;
    }
}
