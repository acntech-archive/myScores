package myscores.services;

import myscores.domain.Match;
import myscores.repositories.MatchRepository;

import javax.inject.Inject;
import java.util.List;

public class MatchService {

    @Inject
    private MatchRepository repository;

    public Match get(String name) {
        return repository.read(name);
    }

    public List<Match> list() {
        return repository.find();
    }

    public boolean register(Match match) {
        return repository.create("" + match.getId(), match) != null;
    }
}
