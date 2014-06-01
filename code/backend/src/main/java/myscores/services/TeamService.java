package myscores.services;

import myscores.domain.Team;
import myscores.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class TeamService extends Service<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    @Inject
    private TeamRepository repository;

    @Override
    public Team get(int id) {
        LOGGER.info("Get team for id {}", id);
        try {
            return repository.read(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting team for id " + id, e);
        }
    }

    @Override
    public List<Team> find() {
        LOGGER.info("Find teams");
        try {
            return repository.find();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while finding teams", e);
        }
    }

    @Override
    public void register(Team team) {
        LOGGER.info("Register team {}", team.getName());
        try {
            repository.create(team);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering team " + team.getName(), e);
        }
    }

    @Override
    public void change(Team team) {
        LOGGER.info("Change team {} with id {}", team.getName(), team.getId());
        try {
            repository.update(team);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while changing team " + team.getName() + " with id " + team.getId(), e);
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete team with id {}", id);
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting team with id " + id, e);
        }
    }
}
