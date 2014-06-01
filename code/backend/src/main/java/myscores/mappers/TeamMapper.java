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
            Team team = new Team();
            team.setId(getIdProperty(node));
            team.setName(getNameProperty(node));
            team.setFifaRanking(getIntProperty(node, Props.RANK));
            return team;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Team team) {
        if (node != null && team != null) {
            node.addLabel(createLabel());
            node.setProperty(Props.ID, team.getId());
            node.setProperty(Props.NAME, team.getName());
            node.setProperty(Props.RANK, team.getFifaRanking());
            return node;
        } else {
            return null;
        }
    }

    @Override
    public Label createLabel() {
        return createLabel(Team.class);
    }
}
