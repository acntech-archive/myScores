package myscores.mappers;

import myscores.constants.Key;
import myscores.domain.Team;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeamMapper extends NodeMapper<Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamMapper.class);

    @Override
    public Team map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            LOGGER.debug("Mapping node to domain object");
            Team team = new Team();
            team.setId(getIntProperty(node, Key.ID));
            team.setName(getStringProperty(node, Key.NAME));
            team.setFifaRanking(getIntProperty(node, Key.RANK));
            return team;
        } else {
            LOGGER.debug("Node is null or has wrong label");
            return null;
        }
    }

    @Override
    public Node map(Node node, Team team) {
        if (node != null && team != null) {
            LOGGER.debug("Mapping domain object to node");
            node.addLabel(createLabel());
            setProperty(node, Key.ID, team.getId());
            setProperty(node, Key.NAME, team.getName());
            setProperty(node, Key.RANK, team.getFifaRanking());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public Key getDefaultKey() {
        return Key.ID;
    }

    @Override
    public Label createLabel() {
        return createLabel(Team.class);
    }
}
