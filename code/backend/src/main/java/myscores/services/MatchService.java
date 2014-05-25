package myscores.services;

import myscores.domain.Match;
import myscores.repositories.MatchRepository;
import myscores.repositories.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class MatchService extends Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

    @Inject
    private MatchRepository repository;

    public Match get(int id) {
        LOGGER.info("Get match for id {}", id);
        return repository.read(id);
    }

    public List<Match> find() {
        LOGGER.info("Find matches");
        return repository.find();
    }

    public boolean register(Match match) {
        int id = repository.getNextId();
        LOGGER.info("Register match with id {}", id);
        try {
            repository.create(match);
            return Boolean.TRUE;
        } catch (RepositoryException e) {
            LOGGER.error("Error while registering match with id " + id, e);
            return Boolean.FALSE;
        }
    }
}
