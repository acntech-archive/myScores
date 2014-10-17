package myscores.repositories;

import myscores.constants.Key;
import myscores.database.LocalGraphDatabase;
import myscores.constants.NodeIndex;
import myscores.domain.Team;
import myscores.mappers.TeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TeamRepository extends AbstractRepository<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private TeamMapper mapper;

    @Override
    public Team read(Object id) {
        LOGGER.info("Read team for id {}", id);
        return read(database, mapper, NodeIndex.TEAMS, id);
    }

    @Override
    public List<Team> find() {
        LOGGER.info("Find teams");
        return find(database, mapper, NodeIndex.TEAMS);
    }

    @Override
    public void create(Team team) {
        int id = nextId();
        LOGGER.info("Create team with id {}", id);
        team.setId(id);
        create(database, mapper, NodeIndex.TEAMS, team, id);
    }

    @Override
    public void update(Team team) {
        LOGGER.info("Update team with id {}", team.getId());
        update(database, mapper, NodeIndex.TEAMS, team.getId(), keyValue(Key.NAME, team.getName()));
    }

    @Override
    public void delete(Object id) {
        LOGGER.info("Delete team with id {}", id);
        delete(database, mapper, NodeIndex.TEAMS, id);
    }

    @Override
    public int nextId() {
        return nextId(database, mapper, NodeIndex.TEAMS);
    }
}
