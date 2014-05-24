package myscores.services;

import myscores.domain.Team;
import myscores.repositories.TeamRepository;

import javax.inject.Inject;
import java.util.List;

public class TeamService {

    @Inject
    private TeamRepository repository;

    public Team get(String name) {
        return repository.read(name);
    }

    public List<Team> find() {
        return repository.find();
    }

    public boolean register(Team team) {
        return repository.create("" + team.getId(), team) != null;
    }
}
