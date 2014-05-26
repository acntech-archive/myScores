package myscores.domain;

import java.util.List;

public class Party {

    private int id;
    private String name;
    private List<Gambler> gamblers;

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

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public void setGamblers(List<Gambler> gamblers) {
        this.gamblers = gamblers;
    }
}
