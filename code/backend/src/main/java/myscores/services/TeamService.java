package myscores.services;

import myscores.domain.Team;
import myscores.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    @Inject
    private TeamRepository repository;

    public Team get(String name) {
        LOGGER.info("Get match for name {}", name);
        return repository.read(name);
    }

    public List<Team> find() {
        LOGGER.info("Find matches");
        return repository.find();
    }

    public boolean register(Team team) {
        String id = "" + team.getId();
        LOGGER.info("Register match with id {}", id);
        repository.create(id, team);
        return Boolean.TRUE;
    }
}
