package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Team;
import myscores.mappers.TeamMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository extends Repository<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private TeamMapper mapper;

    @Override
    public Team read(int id) {
        LOGGER.info("Read team for id {}", id);
        Team team = null;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            if (node != null) {
                team = mapper.mapNode(node);
            } else {
                LOGGER.warn("Read failed. No team found for id {}", id);
            }
            tx.success();
        }
        return team;
    }

    @Override
    public List<Team> find() {
        LOGGER.info("Find teams");
        List<Team> teams = new ArrayList<>();
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            IndexHits<Node> nodes = index.query(Props.ID, Props.ALL);
            if (nodes != null) {
                for (Node node : nodes) {
                    teams.add(mapper.mapNode(node));
                }
            } else {
                LOGGER.warn("Find failed. No teams found");
            }
            tx.success();
        }
        return teams;
    }

    @Override
    public void create(Team team) {
        LOGGER.info("Create team with id {}", team.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            if (index.get(Props.ID, team.getId()).getSingle() == null) {
                Node node = mapper.mapNode(database.createNode(), team);
                index.add(node, Props.ID, team.getId());
            } else {
                LOGGER.warn("Create failed. Team with id {} already exists", team.getId());
            }
            tx.success();
        }
    }

    @Override
    public void update(Team team) {
        LOGGER.info("Update team with id {}", team.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = index.get(Props.ID, team.getId()).getSingle();
            if (node != null) {
                node.setProperty(Props.NAME, team.getName());
            } else {
                LOGGER.warn("Update failed. No team found for id {}", team.getId());
            }
            tx.success();
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete team with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.TEAMS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                node.delete();
            } else {
                LOGGER.warn("Delete failed. No team found for id {}", id);
            }
            tx.success();
        }
    }
}
