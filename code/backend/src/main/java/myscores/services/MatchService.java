package myscores.services;

import myscores.domain.Match;
import myscores.repositories.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class MatchService extends Service<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

    @Inject
    private MatchRepository repository;

    @Override
    public Match get(int id) {
        LOGGER.info("Get match for id {}", id);
        try {
            return repository.read(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting match for id " + id, e);
        }
    }

    @Override
    public List<Match> find() {
        LOGGER.info("Find matches");
        try {
            return repository.find();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while finding matches", e);
        }
    }

    @Override
    public void register(Match match) {
        int id = repository.getNextId();
        LOGGER.info("Register match with id {}", id);
        try {
            repository.create(match);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering match with id " + id, e);
        }
    }
}
