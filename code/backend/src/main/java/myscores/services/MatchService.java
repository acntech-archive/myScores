package myscores.services;

import myscores.domain.Match;
import myscores.repositories.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class MatchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

    @Inject
    private MatchRepository repository;

    public Match get(String name) {
        LOGGER.info("Get match for name {}", name);
        return repository.read(name);
    }

    public List<Match> find() {
        LOGGER.info("Find matches");
        return repository.find();
    }

    public boolean register(Match match) {
        String id = "" + match.getId();
        LOGGER.info("Register match with id {}", id);
        repository.create(id, match);
        return Boolean.TRUE;
    }
}
