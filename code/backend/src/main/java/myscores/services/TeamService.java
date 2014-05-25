package myscores.services;

import myscores.domain.Team;
import myscores.repositories.RepositoryException;
import myscores.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class TeamService extends Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    @Inject
    private TeamRepository repository;

    public Team get(int id) {
        LOGGER.info("Get team for id {}", id);
        return repository.read(id);
    }

    public List<Team> find() {
        LOGGER.info("Find teams");
        return repository.find();
    }

    public boolean register(Team team) {
        int id = repository.getNextId();
        LOGGER.info("Register team with id {}", id);
        try {
            repository.create(team);
            return Boolean.TRUE;
        } catch (RepositoryException e) {
            LOGGER.error("Error while registering team with id " + id, e);
            return Boolean.FALSE;
        }

    }
}
