package myscores.services;

import myscores.domain.Match;
import myscores.repositories.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
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
        LOGGER.info("Register match");
        try {
            repository.create(match);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering match", e);
        }
    }

    @Override
    public void change(Match match) {
        int id = match.getId();
        LOGGER.info("Change match with id {}", id);
        try {
            repository.update(match);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while changing match with id " + id, e);
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete match with id {}", id);
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting match with id " + id, e);
        }
    }
}
