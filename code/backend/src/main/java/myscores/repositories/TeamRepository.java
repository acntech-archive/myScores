package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Team;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class TeamRepository extends Repository<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Override
    public Team read(int id) {
        LOGGER.info("Read team for id {}", id);
        Team team;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            team = new Team();
            team.setId((Integer) node.getProperty(Props.ID));
            tx.success();
        }
        return team;
    }

    @Override
    public List<Team> find() {
        LOGGER.info("Find teams");
        return null;
    }

    @Override
    public void create(Team team) {
        LOGGER.info("Create team with id {}", team.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = database.createNode();
            node.setProperty(Props.ID, team.getId());
            index.add(node, Props.ID, team.getId());
            tx.success();
        }
    }

    @Override
    public void update(Team team) {
        LOGGER.info("Update team with id {}", team.getId());
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete team with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            index.remove(node, Props.ID, node.getProperty(Props.ID));
            node.delete();
            tx.success();
        }
    }
}
