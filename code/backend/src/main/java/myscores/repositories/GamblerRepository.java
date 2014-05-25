package myscores.repositories;

import myscores.domain.Gambler;

import java.util.HashMap;
import java.util.Map;

public class GamblerRepository extends Repository<Gambler> {

    private static Map<String, Gambler> REPO = new HashMap<>();

    static {
        for (int i = 1; i < 10; i++) {
            REPO.put("" + i, create(i));
        }
    }

    @Override
    protected Map<String, Gambler> getRepo() {
        return REPO;
    }

    private static Gambler create(int id) {
        Gambler gambler = new Gambler();
        gambler.setId(id);
        gambler.setName("Gambler " + id);
        return gambler;
    }
}
