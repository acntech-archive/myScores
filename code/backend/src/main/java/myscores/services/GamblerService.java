package myscores.services;

import myscores.domain.Gambler;
import myscores.repositories.GamblerRepository;

import javax.inject.Inject;
import java.util.List;

public class GamblerService {

    @Inject
    private GamblerRepository repository;

    public Gambler get(String name) {
        return repository.read(name);
    }

    public List<Gambler> list() {
        return repository.find();
    }

    public boolean register(Gambler gambler) {
        return repository.create("" + gambler.getId(), gambler) != null;
    }

    public boolean activate(String name) {
        Gambler gambler = repository.read(name);
        if (gambler == null || gambler.isActive()) {
            return Boolean.FALSE;
        }
        gambler.setActive(Boolean.TRUE);
        return repository.update("" + gambler.getId(), gambler) != null;
    }
}
