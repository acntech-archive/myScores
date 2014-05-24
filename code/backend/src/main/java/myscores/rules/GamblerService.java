package myscores.rules;

import myscores.domain.Gambler;

import java.util.ArrayList;
import java.util.List;

public class GamblerService {

    public Gambler get(String name) {
        return create(1, "John Doe");
    }

    public List<Gambler> list() {
        List<Gambler> gamblers = new ArrayList<>();
        gamblers.add(create(1, "John Doe"));
        gamblers.add(create(2, "Jane Doe"));
        return gamblers;
    }

    public String register(Gambler gambler) {
        return "Registered " + gambler.getName() + " successfully";
    }

    public String activate(String name) {
        return "Activated " + name + " successfully";
    }

    private Gambler create(int id, String name) {
        Gambler gambler = new Gambler();
        gambler.setId(id);
        gambler.setName(name);
        return gambler;
    }
}
