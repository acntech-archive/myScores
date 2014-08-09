package myscores.mappers;

import myscores.database.Props;
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
            team.setId(getIntProperty(node, Props.ID));
            team.setName(getStringProperty(node, Props.NAME));
            team.setFifaRanking(getIntProperty(node, Props.RANK));
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
            node.setProperty(Props.ID, team.getId());
            node.setProperty(Props.NAME, team.getName());
            node.setProperty(Props.RANK, team.getFifaRanking());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public String getDefaultKey() {
        return Props.ID;
    }

    @Override
    public Label createLabel() {
        return createLabel(Team.class);
    }
}
